package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Controller.CRUD.CRUD;
import A1.Apteka.Apteka.MapperDTO.MapperImpl;
import A1.Apteka.Apteka.Model.ModelDTO.UserDTO;
import A1.Apteka.Apteka.Model.User;
import A1.Apteka.Apteka.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController implements CRUD<UserDTO,User> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapperImpl mapper;


    @Override
    @GetMapping(value = "/get/all", produces = "application/json", consumes = "application/json")
    public List<UserDTO> getObjects() {
        return userRepository.findAll().stream().map(mapper::userToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getObject(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> createObject(User object) {
        return null;
    }

    @Override
    public UserDTO updateObject(User object) {
        return null;
    }

    @Override
    public UserDTO deleteObject(User object) {
        return null;
    }
}
