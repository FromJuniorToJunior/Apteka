package A1.Apteka.Apteka.Model.ModelDTO;

import A1.Apteka.Apteka.Model.Anxieties;
import A1.Apteka.Apteka.Model.User;
import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    public OrderDTO(boolean realized, List<AnxietiesDTO> anxieties, String user, double cost) {
        this.realized = realized;
        this.anxieties = anxieties;
        this.user=user;
        this.cost = cost;
    }

    private boolean realized;
    private String user;
    private List<AnxietiesDTO> anxieties;
    private double cost;
}
