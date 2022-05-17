package A1.Apteka.Apteka;

import A1.Apteka.Apteka.Repository.InvoiceRepository;
import A1.Apteka.Apteka.Repository.OrderRepository;
import A1.Apteka.Apteka.Services.InvoiceService;
import A1.Apteka.Apteka.Services.MailService;
import A1.Apteka.Apteka.Services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AptekaApplication {
    @Autowired
    private MailService mailService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    PdfService pdfService;


    /*
        @Bean
        public void real(){
            //invoiceService.saveXlsx(invoiceService.invoice(orderRepository.findOrderById(1L)),"text.xlsx");
            *//*invoiceService.saveXlsxToDB(invoiceService.invoice(orderRepository.findOrderById(1L)),orderRepository.findOrderById(1L)
				.getUser());*//*

	}*/
/*	@Bean
	public void testMessage(){
		mailService.sendMail(orderRepository.findOrderById(1L).getUser());

	}*/
/*@Bean
public void test(){
	try {
		XSSFWorkbook workbook =new XSSFWorkbook(new File("./ObszarKancelaria.xlsx"));

		StaticGenerator.generateInsert(workbook, "initial_field");


	} catch (IOException e) {
		e.printStackTrace();
	} catch (InvalidFormatException e) {
		e.printStackTrace();
	}
}*/
   /* @Bean
    public void OCR() {
        ITesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath("C:/Users/jaroslaw.bondaruk/Documents/tessdata");
            tesseract.setLanguage("pol");
            tesseract.setOcrEngineMode(3);
            tesseract.setPageSegMode(1);
            String test = tesseract.doOCR(new File("./OCR/ocr2.jpg"));

            System.out.println(test);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }*/

    public static void main(String[] args) {
        SpringApplication.run(AptekaApplication.class, args);
    }

}
