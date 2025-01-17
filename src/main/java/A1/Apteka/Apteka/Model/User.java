package A1.Apteka.Apteka.Model;

import A1.Apteka.Apteka.Enum.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(length = 10)
    private String nickname;
    @Column(length = 16)
    private String name;
    @Column(length = 20)
    private String surname;
    @Column
    private Gender gender;
    @Column
    private int age;
    @Column(length = 25, unique = true)
    private String email;
    @Column(length = 70)
    private String password;
    @Column(length = 12)
    private String phone;
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comment = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Invoice> invoices;

}
