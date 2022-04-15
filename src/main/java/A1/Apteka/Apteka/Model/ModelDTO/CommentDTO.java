package A1.Apteka.Apteka.Model.ModelDTO;

import A1.Apteka.Apteka.Model.Anxieties;
import A1.Apteka.Apteka.Model.User;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Data
public class CommentDTO {
    public CommentDTO(String content, String date, String user) {
        this.content = content;
        this.date = date;
        this.user = user;
    }

    private String content;
    private String date;
    private String user;
}

