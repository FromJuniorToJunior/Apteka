package A1.Apteka.Apteka.Model.ModelDTO;

import A1.Apteka.Apteka.Model.Anxieties;
import A1.Apteka.Apteka.Model.User;
import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    private boolean realized;
    private User user;
    private List<Anxieties> anxieties;
}
