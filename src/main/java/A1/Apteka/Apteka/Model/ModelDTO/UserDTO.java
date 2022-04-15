package A1.Apteka.Apteka.Model.ModelDTO;

import A1.Apteka.Apteka.Enum.Gender;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    public UserDTO(String nickname, String name, String surname, Gender gender,
                   int age, String email, String phone, List<CommentDTO> comment,
                   AddressDTO address, List<OrderDTO> orders) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
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
    private Gender gender;
    private int age;
    private String email;
    private String phone;
    private List<CommentDTO> comment;
    private AddressDTO address;
    private List<OrderDTO> orders;

}
