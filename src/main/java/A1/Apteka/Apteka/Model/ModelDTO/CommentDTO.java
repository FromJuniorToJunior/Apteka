package A1.Apteka.Apteka.Model.ModelDTO;

import A1.Apteka.Apteka.Model.Anxieties;
import A1.Apteka.Apteka.Model.User;
import lombok.*;

import java.text.DateFormat;

@Data
public class CommentDTO {
    private String content;
    private DateFormat date;
    private User user;
}

