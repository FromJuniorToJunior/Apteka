package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Model.Address;
import A1.Apteka.Apteka.Model.ModelDTO.AddressDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressCRUD {
    //GET
    List<AddressDTO> getAddresses();
    AddressDTO getAddress(Long id);

    //Create
    ResponseEntity<String> createAddress(Address address);

    //Update
    AddressDTO updateAddress(Address address);

    //Delete
    AddressDTO deleteAddress(Address address);
}
