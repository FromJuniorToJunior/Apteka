package A1.Apteka.Apteka.Repository;

import A1.Apteka.Apteka.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.user_id = ?1")
    User findByUserId(Long id);
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
