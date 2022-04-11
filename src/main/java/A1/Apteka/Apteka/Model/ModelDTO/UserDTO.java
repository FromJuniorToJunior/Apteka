package A1.Apteka.Apteka.Model.ModelDTO;

import A1.Apteka.Apteka.Enum.Gender;
import A1.Apteka.Apteka.Model.Address;
import A1.Apteka.Apteka.Model.Comment;
import A1.Apteka.Apteka.Model.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    public UserDTO(String nickname, String name, String surname, Gender gender,
                   String age, String email, String phone, List<String> comment,
                   String address, List<String> orders) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.gender = gender.toString();
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
        this.address = address;
        this.orders = orders;
    }

    private String nickname;
    private String name;
    private String surname;
    private String gender;
    private String age;
    private String email;
    private String phone;
    private List<String> comment;
    private String address;
    private List<String> orders;

}
