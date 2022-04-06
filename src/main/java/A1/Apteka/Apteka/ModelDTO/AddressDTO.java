package A1.Apteka.Apteka.ModelDTO;

import A1.Apteka.Apteka.Model.User;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Data ->
 * @ToString,
 * @EqualsAndHashCode,
 * @Getter on all fields,
 * @Setter on all non-final fields,
 * @RequiredArgsConstructor
 **/
@Data
public class AddressDTO {
    private Long address_id;
    private String country;
    private String city;
    private String street;
    private String bnumer;
    private String lnumber;
    private List<User> users;
}
