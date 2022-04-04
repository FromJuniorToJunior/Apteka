package A1.Apteka.Apteka.Repository;

import A1.Apteka.Apteka.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
