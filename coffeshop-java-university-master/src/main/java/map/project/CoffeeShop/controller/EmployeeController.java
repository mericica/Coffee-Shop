package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.model.Employee;
import map.project.CoffeeShop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public Employee create(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping("/all")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @DeleteMapping("delete/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") int employeeId) {
        employeeService.delete(employeeId);
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") int employeeId) {
        return employeeService.findById(employeeId);
    }
}