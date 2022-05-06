package A1.Apteka.Apteka;

import A1.Apteka.Apteka.Repository.InvoiceRepository;
import A1.Apteka.Apteka.Repository.OrderRepository;
import A1.Apteka.Apteka.Services.InvoiceService;
import A1.Apteka.Apteka.Services.MailService;
import A1.Apteka.Apteka.Services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;


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

	public static void main(String[] args) {
		SpringApplication.run(AptekaApplication.class, args);
	}

}
