package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Model.Order;
import A1.Apteka.Apteka.Repository.InvoiceRepository;
import A1.Apteka.Apteka.Repository.OrderRepository;
import A1.Apteka.Apteka.Services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoiceController{
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private OrderRepository orderRepository;

@Autowired
private InvoiceRepository invoiceRepository;
    @GetMapping("/test")
    private void test(){

    }


}
