package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Controller.CRUD.CRUD;
import A1.Apteka.Apteka.MapperDTO.MapperImpl;
import A1.Apteka.Apteka.Model.ModelDTO.OrderDTO;
import A1.Apteka.Apteka.Model.Order;
import A1.Apteka.Apteka.Model.User;
import A1.Apteka.Apteka.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController implements CRUD<OrderDTO, Order> {
    @Autowired
    private HttpSession session;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MapperImpl mapper;


    @Override
    @GetMapping(value = "/get/orders", produces = "application/json")
    public List<OrderDTO> getObjects() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::orderToDTO).collect(Collectors.toList());
    }

    @Override
    @GetMapping(value = "/get/order", produces = "application/json")
    public @ResponseBody
    OrderDTO getObject(@RequestParam("id") Long id) {
        try {
            return mapper.orderToDTO(orderRepository.findOrderById(id));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @PostMapping(value = "/create/order", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createObject(Order object) {
        try {
            object.setUser((User) session.getAttribute("user"));
            orderRepository.save(object);
            return ResponseEntity.ok().body("Order created:" + System.lineSeparator() + mapper.orderToDTO(object));
        } catch (Exception e) {
            return ResponseEntity.ok().body("Cannot create order: " + System.lineSeparator() + mapper.orderToDTO(object));
        }
    }
//ToDo updateOrder (not important)
    @Override
    public OrderDTO updateObject(Order object) {
        return null;
    }

    @Override
    @DeleteMapping(value = "/delete/order")
    public OrderDTO deleteObject(Order object) {
        try {
            orderRepository.delete(orderRepository.findOrderById(object.getOrder_id()));
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return mapper.orderToDTO(object);
    }


}
