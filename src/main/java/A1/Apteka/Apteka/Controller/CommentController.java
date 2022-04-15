package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Controller.CRUD.CRUD;
import A1.Apteka.Apteka.MapperDTO.MapperImpl;
import A1.Apteka.Apteka.Model.Comment;
import A1.Apteka.Apteka.Model.ModelDTO.CommentDTO;
import A1.Apteka.Apteka.Model.User;
import A1.Apteka.Apteka.Repository.AnxietiesRepository;
import A1.Apteka.Apteka.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController implements CRUD<CommentDTO, Comment> {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private HttpSession session;
    @Autowired
    private AnxietiesRepository anxietiesRepository;
    @Autowired
    private MapperImpl mapper;

    @Override
    @GetMapping(value = "/comments", produces = "application/json")
    public List<CommentDTO> getObjects() {
        return commentRepository.findAll()
                .stream()
                .map(mapper::commentToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @GetMapping(value = "/comment", produces = "application/json")
    public CommentDTO getObject(@RequestParam Long id) {
        try {
            return mapper.commentToDTO(commentRepository.findCommentById(id));

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createObject(@RequestBody Comment object) {
        Comment comment = new Comment();
        try {
            comment.setContent(object.getContent());

            comment.setAnxieties(anxietiesRepository.findAnxByName(object.getAnxieties().getName()));
            comment.setUser((User) session.getAttribute("user"));
            comment.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            comment.getAnxieties().getComments().add(comment);
            commentRepository.save(comment);


            return ResponseEntity.ok().body("Comment created: " + System.lineSeparator() + mapper.commentToDTO(comment));
        } catch (Exception e) {
            return null;
        }
    }

    //ToDo to do but not important
    @Override
    public CommentDTO updateObject(Comment object) {
        return null;
    }

    @Override
    public CommentDTO deleteObject(Comment object) {
        return null;
    }
}
