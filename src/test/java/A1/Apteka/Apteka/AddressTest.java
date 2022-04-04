package A1.Apteka.Apteka;

import A1.Apteka.Apteka.Model.Address;
import A1.Apteka.Apteka.Repository.AddressRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressTest {
    @Autowired
    AddressRepository addressRepository;
    Address address = new Address();

    public AddressTest() {
        address.setCity("Białystok");
        address.setCountry("Poland");
        address.setStreet("Nowa");
        address.setLnumber("27");
        address.setBnumer("62");
    }

    @Test
    public void addAddress() {
        addressRepository.save(address);
       Assert.assertNotEquals(0, addressRepository.findAll());
    }
    @Test
    public void getAddress(){
        addressRepository.save(address);
        Assert.assertEquals(address,addressRepository.znajdz(address.getAddress_id()));
    }
}
