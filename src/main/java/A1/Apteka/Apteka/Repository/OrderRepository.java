package A1.Apteka.Apteka.Repository;

import A1.Apteka.Apteka.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.order_id = ?1")
    Order findOrderById(Long id);
}
