package A1.Apteka.Apteka.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    @Column
    private boolean realized;
    @Column
    private double cost = 0;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "anxieties_orders",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "anxieties_id")}
    )
    private List<Anxieties> anxieties = new ArrayList<>();
}
