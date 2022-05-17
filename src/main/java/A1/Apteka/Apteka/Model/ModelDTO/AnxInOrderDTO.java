package A1.Apteka.Apteka.Model.ModelDTO;

import A1.Apteka.Apteka.Model.Anxieties;
import A1.Apteka.Apteka.Model.Order;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
public class AnxInOrderDTO {

    public AnxInOrderDTO(AnxietiesForUserDTO anxieties, int amount) {
        this.anxieties = anxieties;
        this.amount = amount;
    }

    private AnxietiesForUserDTO anxieties;
    private int amount;
}
