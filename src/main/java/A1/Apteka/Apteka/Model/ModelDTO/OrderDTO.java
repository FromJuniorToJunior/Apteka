package A1.Apteka.Apteka.Model.ModelDTO;

import A1.Apteka.Apteka.Model.Anxieties;
import A1.Apteka.Apteka.Model.User;
import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    public OrderDTO(boolean realized, UserDTO user, List<AnxietiesDTO> anxieties) {
        this.realized = realized;
        this.user = user;
        this.anxieties = anxieties;
    }

    private boolean realized;
    private UserDTO user;
    private List<AnxietiesDTO> anxieties;
}
