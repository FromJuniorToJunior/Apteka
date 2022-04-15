package A1.Apteka.Apteka.Repository;

import A1.Apteka.Apteka.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a WHERE a.address_id = ?1")
    Address znajdz(Long id);

}
