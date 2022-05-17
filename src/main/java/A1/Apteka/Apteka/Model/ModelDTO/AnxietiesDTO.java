package A1.Apteka.Apteka.Model.ModelDTO;

import lombok.Data;

import java.util.Base64;


@Data
public class AnxietiesDTO {
    public AnxietiesDTO(String name, double price, boolean otc, byte[] img, int amount, String unit, int taxRate, String description) {
        this.name = name;
        this.price = price;
        this.otc = otc;
        this.img = Base64.getMimeEncoder().encodeToString(img);
        this.amount = amount;
        this.unit = unit;
        this.taxRate = taxRate;
        this.description = description;
    }

    private String name;
    private double price;
    private boolean otc;
    private String img;
    private int amount;
    private String unit;
    private int taxRate;
    private String description;


}
