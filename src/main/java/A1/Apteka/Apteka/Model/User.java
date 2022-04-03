package A1.Apteka.Apteka.Model;

import A1.Apteka.Apteka.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(length = 30)
    private String password;
    @Column(length = 12)
    private String phone;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Comment> comment;
    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Order> orders;

}
