package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Controller.CRUD.CRUD;
import A1.Apteka.Apteka.Model.Comment;
import A1.Apteka.Apteka.Model.ModelDTO.CommentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CommentController implements CRUD<CommentDTO,Comment> {

    @Override
    public List<CommentDTO> getObjects() {
        return null;
    }

    @Override
    public CommentDTO getObject(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> createObject(Comment object) {
        return null;
    }

    @Override
    public CommentDTO updateObject(Comment object) {
        return null;
    }

    @Override
    public CommentDTO deleteObject(Comment object) {
        return null;
    }
}
