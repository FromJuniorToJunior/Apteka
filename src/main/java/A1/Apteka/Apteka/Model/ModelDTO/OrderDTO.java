package A1.Apteka.Apteka.Model.ModelDTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    public OrderDTO(boolean realized, List<AnxietiesDTO> anxieties, String user, double cost, String date) {
        this.realized = realized;
        this.anxieties = anxieties;
        this.user = user;
        this.cost = cost;
        this.date = date;
    }

    private boolean realized;
    private String user;
    private List<AnxietiesDTO> anxieties;
    private double cost;
    private String date;
}
