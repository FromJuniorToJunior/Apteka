package A1.Apteka.Apteka.Model.ModelDTO;
import A1.Apteka.Apteka.Model.Comment;
import A1.Apteka.Apteka.Model.Order;
import lombok.Data;
import java.util.List;

@Data
public class AnxietiesDTO {
    private String name;
    private double price;
    private boolean otc;
    private byte[] img;


}
