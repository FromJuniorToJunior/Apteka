package A1.Apteka.Apteka.Services;

import A1.Apteka.Apteka.MapperDTO.MapperImpl;
import A1.Apteka.Apteka.Model.Address;
import A1.Apteka.Apteka.Model.ModelDTO.AddressDTO;
import A1.Apteka.Apteka.Model.ModelDTO.UserDTO;
import A1.Apteka.Apteka.Model.User;
import A1.Apteka.Apteka.Repository.AddressRepository;
import A1.Apteka.Apteka.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateServiceCRUD {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapperImpl mapper;

    public AddressDTO updateAddress(Address address) {
        try {
            if (addressRepository.znajdz(address.getAddress_id()) != null) {
                Address old = addressRepository.znajdz(address.getAddress_id());
                old.setCity(address.getCity());
                old.setCountry(address.getCountry());
                old.setStreet(address.getStreet());
                old.setBnumer(address.getBnumer());
                old.setLnumber(address.getLnumber());
                addressRepository.save(old);
                return mapper.addressToDTO(address);
            }
            return null;

        } catch (Exception e) {
            return null;
        }
    }

    public UserDTO updateUser(User user) {
        try {
            User old = userRepository.findByUserId(user.getUser_id());
            if (old != null) {
                old.setAge(user.getAge());
                old.setEmail(user.getEmail());
                old.setGender(user.getGender());
                old.setName(user.getName());
                old.setNickname(user.getNickname());
                old.setPhone(user.getPhone());
                old.setSurname(user.getSurname());
                userRepository.save(old);
            }
            return mapper.userToDTO(old);
        } catch (Exception e) {
            return null;
        }
    }
}
