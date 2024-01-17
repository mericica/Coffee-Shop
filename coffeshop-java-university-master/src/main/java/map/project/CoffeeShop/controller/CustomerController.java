package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.model.Customer;
import map.project.CoffeeShop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer) {
        return customerService.save(customer);

    }

    @GetMapping("/all")
    public List<Customer> getAll() {
        return customerService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Customer> getByID(@PathVariable("id") int id) {
        return customerService.getByID(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable int id) {
        customerService.deleteById(id);
    }
}
