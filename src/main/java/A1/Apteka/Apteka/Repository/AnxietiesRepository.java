package A1.Apteka.Apteka.Repository;

import A1.Apteka.Apteka.Model.Anxieties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnxietiesRepository extends JpaRepository<Anxieties, Long> {
    @Query("SELECT a FROM Anxieties a WHERE a.anxieties_id = ?1")
    Anxieties findAnxietiesById(Long id);

    @Query("SELECT a FROM Anxieties a WHERE a.name = ?1")
    Anxieties findAnxByName(String name);
}
