package A1.Apteka.Apteka.Model.ModelDTO;
import A1.Apteka.Apteka.Model.Comment;
import A1.Apteka.Apteka.Model.Order;
import lombok.Data;
import java.util.List;

@Data
public class AnxietiesDTO {
    public AnxietiesDTO(String name, double price, boolean otc, byte[] img, int amount) {
        this.name = name;
        this.price = price;
        this.otc = otc;
        this.img = img;
        this.amount = amount;
    }

    private String name;
    private double price;
    private boolean otc;
    private byte[] img;
    private int amount;


}
