package A1.Apteka.Apteka.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "anxInOrder")
public class AnxInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long anxInOrder_id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Anxieties anxieties;
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;
    @Column(length = 4)
    private int amount;

    public AnxInOrder() {
    }

    public AnxInOrder(Anxieties anxieties, Order order, int amount) {
        this.anxieties = anxieties;
        this.order = order;
        this.amount = amount;
    }
}
