package A1.Apteka.Apteka.MapperDTO;

import A1.Apteka.Apteka.Model.*;
import A1.Apteka.Apteka.Model.ModelDTO.*;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements ModelMapper{
    @Override
    public AddressDTO addressToDTO(Address address) {
      return new AddressDTO(address.getCountry(), address.getCity(), address.getStreet(), address.getBnumer(), address.getLnumber());
    }

    @Override
    public AnxietiesDTO anxietiesToDTO(Anxieties anxieties) {
        return null;
    }

    @Override
    public CommentDTO commentToDTO(Comment comment) {
        return null;
    }

    @Override
    public OrderDTO orderToDTO(Order order) {
        return null;
    }

    @Override
    public UserDTO userToDTO(User user) {
        return null;
    }
}
