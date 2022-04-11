package A1.Apteka.Apteka.Controller.CRUD;

import A1.Apteka.Apteka.Model.Address;
import A1.Apteka.Apteka.Model.ModelDTO.AddressDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CRUD <T, G>{
    //GET
    List<T> getObjects();
    T getObject(Long id);

    //Create
    ResponseEntity<String> createObject(G object);

    //Update
    T updateObject(G object);

    //Delete
    T deleteObject(G object);
}
