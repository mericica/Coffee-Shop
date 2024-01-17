package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.model.Order;
import map.project.CoffeeShop.data.model.OrderProduct;
import map.project.CoffeeShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public Optional<Order> create(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping("/all")
    public List<Order> getAll() {
        return orderService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Order> getByID(@PathVariable("id") int id) {
        return orderService.getByID(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable int id) {
        orderService.deleteById(id);
    }

    @GetMapping(path = "/getTotalPrice/{id}")
    public String getOrderTotalPriceByID(@PathVariable("id") int id) {
        return orderService.getOrderTotalPriceById(id);
    }

    @PutMapping("/addProduct/{orderId}/{productId}/{count}")
    public OrderProduct addProduct(@PathVariable("orderId") int orderId, @PathVariable("productId") int productId, @PathVariable("count") int count) {
        return orderService.addProduct(orderId, productId, count);
    }

    @PostMapping("/removeProduct/{orderId}/{productId}/{count}")
    public void removeProduct(@PathVariable("orderId") int orderId, @PathVariable("productId") int productId, @PathVariable("count") int quantity) {
        orderService.deleteByOrderProductId(orderId, productId, quantity);
    }


}
