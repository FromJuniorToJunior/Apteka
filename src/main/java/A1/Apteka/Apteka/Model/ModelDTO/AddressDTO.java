package A1.Apteka.Apteka.Model.ModelDTO;

import lombok.Data;

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
