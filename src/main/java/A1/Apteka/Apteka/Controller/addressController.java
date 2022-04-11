package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Controller.CRUD.CRUD;
import A1.Apteka.Apteka.MapperDTO.MapperImpl;
import A1.Apteka.Apteka.Model.Address;
import A1.Apteka.Apteka.Model.ModelDTO.AddressDTO;
import A1.Apteka.Apteka.Repository.AddressRepository;
import A1.Apteka.Apteka.Services.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class addressController implements CRUD<AddressDTO,Address> {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MapperImpl mapper;
    @Autowired
    private AddressService addressService;

    final private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    /**
     * Post - > body - > raw to create new address
     * {
     *
     * @"country" : "Poland",
     * @"city" : "Białystok",
     * @"street" : "Nowa",
     * @"bnumer" : "60",
     * @"lnumber" : "30"
     * }
     */
    @Override
    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<String> createObject(@RequestBody Address address) {

        try {
            addressRepository.save(address);

            return ResponseEntity.accepted().body(objectWriter.writeValueAsString(address));

        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST + "");
        }
    }

    @Override
    @GetMapping(value = "/get/all", produces = "application/json")
    public List<AddressDTO> getObjects() {

        return addressRepository.findAll()
                .stream()
                .map(mapper::addressToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @GetMapping(value = "/get", produces = "application/json")
    public AddressDTO getObject(@RequestParam("id") Long id) {
        return mapper.addressToDTO(addressRepository.znajdz(id));
    }

    @Override
    @PostMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public AddressDTO updateObject(@RequestBody Address address) {
        return addressService.updateAddress(address);
    }

    @Override
    @DeleteMapping(value = "/delete", produces = "application/json", consumes = "application/json")
    public AddressDTO deleteObject(@RequestBody Address address) {
        try {
            addressRepository.delete(addressRepository.znajdz(address.getAddress_id()));
        }catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return mapper.addressToDTO(address);
    }

}
