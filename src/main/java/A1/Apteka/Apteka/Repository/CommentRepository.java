package A1.Apteka.Apteka.Repository;

import A1.Apteka.Apteka.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
