package A1.Apteka.Apteka;

import A1.Apteka.Apteka.Repository.OrderRepository;
import A1.Apteka.Apteka.Services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AptekaApplication {
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	OrderRepository orderRepository;
	@Bean
	public void real(){
		invoiceService.saveXlsx(invoiceService.invoice(orderRepository.findOrderById(1L)),"text.xlsx" );
	}

	public static void main(String[] args) {
		SpringApplication.run(AptekaApplication.class, args);
	}

}
