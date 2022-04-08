package A1.Apteka.Apteka.Services;

import A1.Apteka.Apteka.MapperDTO.MapperImpl;
import A1.Apteka.Apteka.Model.Address;
import A1.Apteka.Apteka.Model.ModelDTO.AddressDTO;
import A1.Apteka.Apteka.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    MapperImpl mapper;

    public AddressDTO updateAddress(Address address){
        try{
            if(addressRepository.znajdz(address.getAddress_id())!=null){
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

        }catch (Exception e){
            return null;
        }
    }
}
