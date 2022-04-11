package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Controller.CRUD.CRUD;
import A1.Apteka.Apteka.Model.ModelDTO.OrderDTO;
import A1.Apteka.Apteka.Model.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class OrderController implements CRUD<OrderDTO,Order> {


    @Override
    public List<OrderDTO> getObjects() {
        return null;
    }

    @Override
    public OrderDTO getObject(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> createObject(Order object) {
        return null;
    }

    @Override
    public OrderDTO updateObject(Order object) {
        return null;
    }

    @Override
    public OrderDTO deleteObject(Order object) {
        return null;
    }
}
