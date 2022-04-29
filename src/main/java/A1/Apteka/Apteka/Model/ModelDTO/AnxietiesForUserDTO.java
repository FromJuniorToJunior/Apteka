package A1.Apteka.Apteka.Model.ModelDTO;

import lombok.Data;

import java.util.Base64;

@Data
public class AnxietiesForUserDTO {
    public AnxietiesForUserDTO(String name, double price, boolean otc) {
        this.name = name;
        this.price = price;
        this.otc = otc;

    }

    private String name;
    private double price;
    private boolean otc;

}
