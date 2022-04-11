package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Controller.CRUD.CRUD;
import A1.Apteka.Apteka.Model.Anxieties;
import A1.Apteka.Apteka.Model.ModelDTO.AnxietiesDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AnxietiesController implements CRUD<AnxietiesDTO,Anxieties> {
    @Override
    public List<AnxietiesDTO> getObjects() {
        return null;
    }

    @Override
    public AnxietiesDTO getObject(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> createObject(Anxieties object) {
        return null;
    }

    @Override
    public AnxietiesDTO updateObject(Anxieties object) {
        return null;
    }

    @Override
    public AnxietiesDTO deleteObject(Anxieties object) {
        return null;
    }
}
