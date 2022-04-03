package A1.Apteka.Apteka.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;
    @Column(length = 30)
    private String country;
    @Column(length = 45)
    private String city;
    @Column(length = 30)
    private String street;
    @Column(length = 5)
    private String bnumer;
    @Column(length = 5)
    private String lnumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address", orphanRemoval = true)
    private List<User> users;


}
