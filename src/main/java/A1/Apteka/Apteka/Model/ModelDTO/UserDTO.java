package A1.Apteka.Apteka.Model.ModelDTO;

import A1.Apteka.Apteka.Enum.Gender;
import A1.Apteka.Apteka.Model.Address;
import A1.Apteka.Apteka.Model.Comment;
import A1.Apteka.Apteka.Model.Order;
import lombok.Data;
import java.util.List;
@Data
public class UserDTO {
    private String nickname;
    private String name;
    private String surname;
    private Gender gender;
    private int age;
    private String email;
    private String phone;
    private List<Comment> comment;
    private Address address;
    private List<Order> orders;
}
