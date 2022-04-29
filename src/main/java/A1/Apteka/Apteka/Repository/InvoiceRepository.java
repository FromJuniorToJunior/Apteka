package A1.Apteka.Apteka.Repository;

import A1.Apteka.Apteka.Model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
