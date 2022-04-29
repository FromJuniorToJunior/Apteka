package A1.Apteka.Apteka.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "anxieties")
public class Anxieties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long anxieties_id;
    @Column(length = 30, unique = true)
    private String name;
    @Column
    private double price;
    @Column
    private boolean otc;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] img;
    @Column
    private int amount;
    @Column
    private String unit;
    @Column
    private int taxRate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anxieties")
    private List<Comment> comments;
/*    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "anxieties")
    private List<Order> orders;*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anxieties")
    private List<AnxInOrder> anxInOrders;


}
