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
        return new AddressDTO(address.getCountry(), address.getCity(), address.getStreet(), address.getBnumer(),
                address.getLnumber());
    }

    @Override
    public AnxietiesDTO anxietiesToDTO(Anxieties anxieties) {

        return new AnxietiesDTO(anxieties.getName(), anxieties.getPrice(), anxieties.isOtc(), anxieties.getImg(),
                anxieties.getAmount(), anxieties.getUnit(), anxieties.getTaxRate(),anxieties.getDescription());
    }

    @Override
    public CommentDTO commentToDTO(Comment comment) {
        return new CommentDTO(comment.getContent(), comment.getDate(), comment.getUser().getName() + " "
                + comment.getUser().getSurname());
    }

    @Override
    public OrderDTO orderToDTO(Order order) {
       /* List<AnxietiesDTO> anxietiesDTOList = order.getAnxieties().stream().map(this::anxietiesToDTO)
                .collect(Collectors.toList());*/
        List<AnxInOrderDTO> anxInOrderDTO = order.getAnxInOrders().stream().map(this::anxInOrderToDTO).collect(Collectors.toList());
        return new OrderDTO(order.isRealized(), order.getUser().getName() + " " +
                order.getUser().getSurname(), order.getCost(), order.getDate(),anxInOrderDTO, order.getNumber());
    }

    @Override
    public UserDTO userToDTO(User user) {

        List<CommentDTO> commment = user.getComment().stream().map(this::commentToDTO).collect(Collectors.toList());
        List<OrderDTO> orders = user.getOrders().stream().map(this::orderToDTO).collect(Collectors.toList());
        return new UserDTO(user.getNickname(), user.getName(), user.getSurname(), user.getGender(), user.getAge(),
                user.getEmail(), user.getPhone(), commment, this.addressToDTO(user.getAddress()), orders);
    }

    @Override
    public AnxietiesForUserDTO anxForUserDTO(Anxieties anx) {
        return new AnxietiesForUserDTO(anx.getName(),anx.getPrice(), anx.isOtc(), anx.getDescription());
    }

    @Override
    public AnxInOrderDTO anxInOrderToDTO(AnxInOrder anxInOrder) {
        return new AnxInOrderDTO(this.anxForUserDTO(anxInOrder.getAnxieties()), anxInOrder.getAmount());
    }
}
