package A1.Apteka.Apteka.Model.ModelDTO;

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
    private String country;
    private String city;
    private String street;
    private String bnumer;
    private String lnumber;

    public AddressDTO(String country, String city, String street, String bnumer, String lnumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.bnumer = bnumer;
        this.lnumber = lnumber;
    }
}
