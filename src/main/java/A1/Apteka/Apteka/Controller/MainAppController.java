package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Model.Invoice;
import A1.Apteka.Apteka.Model.User;
import A1.Apteka.Apteka.Repository.InvoiceRepository;
import A1.Apteka.Apteka.Repository.OrderRepository;
import A1.Apteka.Apteka.Services.InvoiceService;
import A1.Apteka.Apteka.Services.MailService;
import A1.Apteka.Apteka.Services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;

@RestController
@RequestMapping("/main")
public class MainAppController {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private HttpSession session;
    @Autowired
    private MailService mailService;
    @Autowired
    private PdfService pdfService;

    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable("fileId") String id) {

        Invoice file = invoiceRepository.getById(new BigDecimal(id).longValue());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getDocName() + "\"")
                .body(new ByteArrayResource(file.getFileByte()));

    }

    @PostMapping(value = "/create/invoice")
    public ResponseEntity<String> createInvoice(@RequestParam("orderNumber") String number) {
        try {
            invoiceService.saveXlsxToDB(invoiceService.invoice(orderRepository.findOrderByNumber(number)));
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }


        return ResponseEntity.ok().body("Invoice created");
    }

    @GetMapping(value = "/message/send")
    public ResponseEntity<String> sendMessage() throws MessagingException {
       // mailService.sendMail((User) session.getAttribute("user"));
        mailService.readMail();

        return ResponseEntity.ok().body("Ok");
    }
    @GetMapping(value = "/pdf")
    public void createPdf(){
        pdfService.writeDocument("test.pdf");
        pdfService.appendDocument();
    }


}
