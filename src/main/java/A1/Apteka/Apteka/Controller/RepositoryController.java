package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Model.Address;
import A1.Apteka.Apteka.Repository.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RepositoryController {
    @Autowired
    private AddressRepository addressRepository;

    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

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
    @PostMapping(value = "/address/create", produces = "application/json")
    public ResponseEntity<String> createUser(@RequestBody Address address) {

        try {
            addressRepository.save(address);

            return ResponseEntity.accepted().body(objectWriter.writeValueAsString(address));

        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST + "");
        }
    }
    //ToDO no nie działa xD
    @GetMapping(value = "/get/addresses", produces = "application/json")
    public ResponseEntity<List<Address>> getAddresses(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Address>> responseEntity =
                restTemplate.exchange(
                        "/get/addresses",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Address>>() {}
                );
        List<Address> addresses = responseEntity.getBody();
        return (ResponseEntity<List<Address>>) addresses.stream()
                .map(Address::getAddress_id)
                .collect(Collectors.toList());
    }

}
