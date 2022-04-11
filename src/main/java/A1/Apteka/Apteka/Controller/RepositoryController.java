package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Model.Address;
import A1.Apteka.Apteka.Model.Anxieties;
import A1.Apteka.Apteka.Repository.AddressRepository;
import A1.Apteka.Apteka.Repository.AnxietiesRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RepositoryController {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AnxietiesRepository anxietiesRepository;

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

    @GetMapping(value = "/get/addresses", produces = "application/json")
    public @ResponseBody
    List<Address> getAddresses() {
        try {
            return addressRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value = "/anxieties/create", produces = "application/json")
    public @ResponseBody
    Anxieties createAnxieties(@RequestParam("name") String name,
                              @RequestParam("otc") boolean otc,
                              @RequestParam("price") Double price,
                              @RequestParam(value = "img") MultipartFile img) {
        Anxieties anxieties = new Anxieties();
        try {
            anxieties.setImg(img.getBytes());
            anxieties.setName(name);
            anxieties.setOtc(otc);
            anxieties.setPrice(price);
            anxietiesRepository.save(anxieties);
            return anxieties;
        } catch (Exception e) {
            return null;
        }
    }


}
