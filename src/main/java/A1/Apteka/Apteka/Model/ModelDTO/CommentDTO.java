package A1.Apteka.Apteka.Model.ModelDTO;

import A1.Apteka.Apteka.Model.Anxieties;
import A1.Apteka.Apteka.Model.User;
import lombok.*;

import java.text.DateFormat;

@Data
public class CommentDTO {
    public CommentDTO(String content, DateFormat date, User user) {
        this.content = content;
        this.date = date;
        this.user = user;
    }

    private String content;
    private DateFormat date;
    private User user;
}

