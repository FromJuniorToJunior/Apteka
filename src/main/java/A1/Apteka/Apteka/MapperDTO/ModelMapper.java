package A1.Apteka.Apteka.MapperDTO;

import A1.Apteka.Apteka.Model.*;
import A1.Apteka.Apteka.Model.ModelDTO.*;

public interface ModelMapper {
    AddressDTO addressToDTO(Address address);

    AnxietiesDTO anxietiesToDTO(Anxieties anxieties);

    CommentDTO commentToDTO(Comment comment);

    OrderDTO orderToDTO(Order order);

    UserDTO userToDTO(User user);

    AnxInOrderDTO anxInOrderToDTO(AnxInOrder anxInOrder);

    AnxietiesForUserDTO anxForUserDTO(Anxieties anx);
}
