package A1.Apteka.Apteka.Model.ModelDTO;

import lombok.Data;

import java.util.Base64;

@Data
public class AnxietiesForUserDTO {
    public AnxietiesForUserDTO(String name, double price, boolean otc, String description) {
        this.name = name;
        this.price = price;
        this.otc = otc;
        this.description=description;

    }

    private String name;
    private double price;
    private boolean otc;
    private String description;

}
