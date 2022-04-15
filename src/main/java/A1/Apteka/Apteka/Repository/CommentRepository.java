package A1.Apteka.Apteka.Repository;

import A1.Apteka.Apteka.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.comment_id = ?1")
    Comment findCommentById(Long id);
}
