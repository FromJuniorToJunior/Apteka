package A1.Apteka.Apteka.ModelDTO;

import A1.Apteka.Apteka.Model.Anxieties;
import A1.Apteka.Apteka.Model.User;
import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    private Long order_id;
    private boolean realized;
    private User user;
    private List<Anxieties> anxieties;
}
