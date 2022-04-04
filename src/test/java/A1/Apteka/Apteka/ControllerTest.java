package A1.Apteka.Apteka;

import A1.Apteka.Apteka.Controller.RepositoryController;
import A1.Apteka.Apteka.Model.Address;
import A1.Apteka.Apteka.Repository.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(RepositoryController.class)
public class ControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AddressRepository addressRepository;


    @Test
    public void createAddressTest() throws Exception {
        Address address = new Address();
        address.setCity("Białystok");
        address.setCountry("Poland");
        address.setStreet("Nowa");
        address.setLnumber("27");
        address.setBnumer("62");
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(MockMvcRequestBuilders.post("/address/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(address)))
                .andExpect(status()
                        .isAccepted());
    }
}
