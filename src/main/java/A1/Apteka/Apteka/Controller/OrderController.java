package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Controller.CRUD.CRUD;
import A1.Apteka.Apteka.MapperDTO.MapperImpl;
import A1.Apteka.Apteka.Model.ModelDTO.OrderDTO;
import A1.Apteka.Apteka.Model.Order;
import A1.Apteka.Apteka.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderController implements CRUD<OrderDTO, Order> {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MapperImpl mapper;


    @Override
    public List<OrderDTO> getObjects() {
        try {
            return orderRepository.findAll()
                    .stream()
                    .map(mapper::orderToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public OrderDTO getObject(Long id) {
        try {
            return mapper.orderToDTO(orderRepository.findOrderById(id));
        } catch (Exception e) {
            return null;
        }
    }
//ToDo OrderToDO
    @Override
    public ResponseEntity<String> createObject(Order object) {
        try{
            return null;
        }catch (Exception e){
            return ResponseEntity.ok().body("Cannot create Order"+ System.lineSeparator() + mapper.orderToDTO(object));
        }
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
