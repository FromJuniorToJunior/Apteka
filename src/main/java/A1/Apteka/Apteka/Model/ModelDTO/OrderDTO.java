package A1.Apteka.Apteka.Model.ModelDTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    public OrderDTO(boolean realized, String user, double cost, String date,List<AnxInOrderDTO> anxInOrderDTO,String number) {
        this.realized = realized;
        this.user = user;
        this.cost = cost;
        this.date = date;
        this.anxInOrderDTO = anxInOrderDTO;
        this.number= number;
    }

    private boolean realized;
    private String user;
    private double cost;
    private String date;
    private List<AnxInOrderDTO> anxInOrderDTO;
    private String number;
}
