package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Controller.CRUD.CRUD;
import A1.Apteka.Apteka.MapperDTO.MapperImpl;
import A1.Apteka.Apteka.Model.Address;
import A1.Apteka.Apteka.Model.ModelDTO.AddressDTO;
import A1.Apteka.Apteka.Model.ModelDTO.UserDTO;
import A1.Apteka.Apteka.Model.User;
import A1.Apteka.Apteka.Repository.AddressRepository;
import A1.Apteka.Apteka.Repository.UserRepository;
import A1.Apteka.Apteka.Services.UpdateServiceCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController implements CRUD<UserDTO, User> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapperImpl mapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UpdateServiceCRUD updateServiceCRUD;


    @Override
    @GetMapping(value = "/get/users", produces = "application/json")
    public List<UserDTO> getObjects() {
        return userRepository.findAll().stream().map(mapper::userToDTO).collect(Collectors.toList());
    }

    @Override
    @GetMapping(value = "/get", produces = "application/json")
    public @ResponseBody
    UserDTO getObject(@RequestParam("id") Long id) {
        try {
            return mapper.userToDTO(userRepository.findByUserId(id));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createObject(@RequestBody User object) {
        try {
            addressRepository.save(object.getAddress());
            userRepository.save(object);
            return ResponseEntity.ok().body("User created: " + System.lineSeparator() + mapper.userToDTO(object));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Process rejected"+ e);
        }
    }

    @Override
    @PostMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public @ResponseBody UserDTO updateObject(@RequestBody User object) {
        return updateServiceCRUD.updateUser(object);
    }

    @Override
    @DeleteMapping(value = "/delete", produces = "application/json", consumes = "application/json")
    public @ResponseBody UserDTO deleteObject(@RequestBody User object) {
            try {
                userRepository.delete(userRepository.findByUserId(object.getUser_id()));
            }catch (Exception e){
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
            return mapper.userToDTO(object);
        }

}
