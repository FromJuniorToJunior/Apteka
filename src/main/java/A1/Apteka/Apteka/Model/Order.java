package A1.Apteka.Apteka.Model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    @Column
    private boolean realized;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "anxieties_orders",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "anxieties_id") }
    )
    private List<Anxieties> anxieties;
}
