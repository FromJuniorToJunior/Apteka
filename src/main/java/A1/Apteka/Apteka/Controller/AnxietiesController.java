package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Controller.CRUD.CRUD;
import A1.Apteka.Apteka.MapperDTO.MapperImpl;
import A1.Apteka.Apteka.Model.Anxieties;
import A1.Apteka.Apteka.Model.ModelDTO.AnxietiesDTO;
import A1.Apteka.Apteka.Repository.AnxietiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/anxieties")
public class AnxietiesController implements CRUD<AnxietiesDTO, Anxieties> {
    @Autowired
    private AnxietiesRepository anxietiesRepository;
    @Autowired
    private MapperImpl mapper;

    @Override
    @GetMapping(value = "/anxieties", produces = "application/json")
    public List<AnxietiesDTO> getObjects() {
        try {
            return anxietiesRepository.findAll()
                    .stream()
                    .map(mapper::anxietiesToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    @Override
    @GetMapping(value = "/anxieties/id")
    public AnxietiesDTO getObject(@RequestParam("id") Long id) {
        try {
            return mapper.anxietiesToDTO(anxietiesRepository.findAnxietiesById(id));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResponseEntity<String> createObject(Anxieties object) {
        return null;
    }

    @RolesAllowed("admin")
    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<String> createObjectProf(@RequestParam("name") String name,
                                                   @RequestParam("price") double price,
                                                   @RequestParam("otc") boolean otc,
                                                   @RequestParam("img") MultipartFile img,
                                                   @RequestParam("amount") int amount,
                                                   @RequestParam("unit") String unit,
                                                   @RequestParam("taxRate") int taxRate,
                                                   @RequestParam("description") String desc) {
        Anxieties object = new Anxieties();
        object.setAmount(amount);
        object.setName(name);
        object.setPrice(price);
        object.setOtc(otc);
        object.setUnit(unit);
        object.setTaxRate(taxRate);
        object.setDescription(desc);
        try {
            object.setImg(img.getBytes());
            anxietiesRepository.save(object);
            return ResponseEntity.ok().body("Anxieties created: " + System.lineSeparator() + mapper.anxietiesToDTO(object));
        } catch (Exception e) {
            return ResponseEntity.ok().body("");
        }
    }

    //ToDo to do but not important
    @Override
    public AnxietiesDTO updateObject(Anxieties object) {
        return null;
    }

    @Override
    public AnxietiesDTO deleteObject(Anxieties object) {
        return null;
    }
}
