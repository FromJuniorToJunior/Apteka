package A1.Apteka.Apteka.Controller;

import A1.Apteka.Apteka.Controller.CRUD.CRUD;
import A1.Apteka.Apteka.MapperDTO.MapperImpl;
import A1.Apteka.Apteka.Model.AnxInOrder;
import A1.Apteka.Apteka.Model.Anxieties;
import A1.Apteka.Apteka.Model.ModelDTO.OrderDTO;
import A1.Apteka.Apteka.Model.Order;
import A1.Apteka.Apteka.Model.User;
import A1.Apteka.Apteka.Repository.AnxietiesRepository;
import A1.Apteka.Apteka.Repository.OrderRepository;
import A1.Apteka.Apteka.Repository.UserRepository;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private AnxietiesRepository anxietiesRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @RolesAllowed("admin")
    @GetMapping(value = "/get/all", produces = "application/json")
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
    public ResponseEntity<String> createObject(Order object) {
        return null;
    }

    @RolesAllowed("user")
    @GetMapping(value = "/get/session", produces = "application/json")
    public List<OrderDTO> getOrdersSession(KeycloakAuthenticationToken keycloakAuthenticationToken){
        SimpleKeycloakAccount account = (SimpleKeycloakAccount) keycloakAuthenticationToken.getDetails();
        AccessToken token = account.getKeycloakSecurityContext().getToken();
        User user = userRepository.findByUserEmail(token.getEmail());
        session.setAttribute("user", user);

        return user.getOrders().stream().map(mapper::orderToDTO).toList();
    }


    @RolesAllowed("user")
    @PostMapping(value = "/create/order", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createObject(@RequestBody Order object,KeycloakAuthenticationToken keycloakAuthenticationToken) {
        SimpleKeycloakAccount account = (SimpleKeycloakAccount) keycloakAuthenticationToken.getDetails();
        AccessToken token = account.getKeycloakSecurityContext().getToken();
        User user = userRepository.findByUserEmail(token.getEmail());
        session.setAttribute("user", user);
        Order or = new Order();

        try {
            or.setUser((User) session.getAttribute("user"));
            or.setRealized(object.isRealized());
          for(AnxInOrder anxInOrder: object.getAnxInOrders()){
              or.getAnxInOrders().add(new AnxInOrder(anxietiesRepository.findAnxByName(anxInOrder.getAnxieties().getName()),or, anxInOrder.getAmount()));
          }
          //Calculate total cost
            for (AnxInOrder anx:or.getAnxInOrders()
                 ) {
                or.setCost(or.getCost()+anx.getAnxieties().getPrice()*anx.getAmount());
            }
          or.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            or.setNumber("INV"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/HH/mm/ss")));

            orderRepository.save(or);

            return ResponseEntity.ok().body("Order created:" + System.lineSeparator()  /*mapper.orderToDTO(or)*/);
        } catch (Exception e) {
            return ResponseEntity.ok().body("Cannot create order: " + System.lineSeparator() /*+ mapper.orderToDTO(or)*/);
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
