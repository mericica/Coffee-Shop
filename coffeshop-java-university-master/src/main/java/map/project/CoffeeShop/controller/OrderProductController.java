package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.model.OrderProduct;
import map.project.CoffeeShop.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order_products")
public class OrderProductController {
    private final OrderProductService orderProductService;

    @Autowired
    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @PostMapping("/create")
    public OrderProduct create(@RequestBody OrderProduct op) {
        return orderProductService.save(op);
    }

    @GetMapping("/all")
    public List<OrderProduct> getAll() {
        return orderProductService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<OrderProduct> getByID(@PathVariable("id") int id) {
        return orderProductService.getByID(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        orderProductService.deleteById(id);
    }
}
