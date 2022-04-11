package A1.Apteka.Apteka.MapperDTO;

import A1.Apteka.Apteka.Model.*;
import A1.Apteka.Apteka.Model.ModelDTO.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperImpl implements ModelMapper {
    @Override
    public AddressDTO addressToDTO(Address address) {
        return new AddressDTO(address.getCountry(), address.getCity(), address.getStreet(), address.getBnumer(), address.getLnumber());
    }

    @Override
    public AnxietiesDTO anxietiesToDTO(Anxieties anxieties) {

        return new AnxietiesDTO(anxieties.getName(), anxieties.getPrice(),anxieties.isOtc(),anxieties.getImg());
    }

    @Override
    public CommentDTO commentToDTO(Comment comment) {
        return new CommentDTO(comment.getContent(),comment.getDate(),comment.getUser());
    }

    @Override
    public OrderDTO orderToDTO(Order order) {
       return null;
    }

    @Override
    public UserDTO userToDTO(User user) {
        List<CommentDTO> commment = user.getComment().stream().map(this::commentToDTO).collect(Collectors.toList());
        List<OrderDTO> orders = user.getOrders().stream().map(this::orderToDTO).collect(Collectors.toList());
        return null;
    }
}
