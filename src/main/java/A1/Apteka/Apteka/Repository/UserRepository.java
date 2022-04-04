package A1.Apteka.Apteka.Repository;

import A1.Apteka.Apteka.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
