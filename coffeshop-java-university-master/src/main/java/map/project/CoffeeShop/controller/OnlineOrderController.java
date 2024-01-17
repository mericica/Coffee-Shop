package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.model.OnlineOrder;
import map.project.CoffeeShop.service.OnlineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/onlineorder")
public class OnlineOrderController {
    private final OnlineOrderService onlineOrderService;

    @Autowired
    public OnlineOrderController(OnlineOrderService onlineOrderService) {
        this.onlineOrderService = onlineOrderService;
    }

    //@PostMapping("/create")
    public Optional<OnlineOrder> create(@RequestBody OnlineOrder order) {
        return onlineOrderService.save(order);

    }

    @GetMapping("/all")
    public List<OnlineOrder> getAll() {
        return onlineOrderService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<OnlineOrder> getByID(@PathVariable("id") int id) {
        return onlineOrderService.getByID(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        onlineOrderService.deleteById(id);
    }
}
