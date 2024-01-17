package map.project.CoffeeShop.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import map.project.CoffeeShop.data.ManagerBuilder;
import map.project.CoffeeShop.data.model.Employee;
import map.project.CoffeeShop.data.model.Manager;
import map.project.CoffeeShop.data.repository.EmployeeDBRepo;
import map.project.CoffeeShop.data.repository.ManagerDBRepo;
import map.project.CoffeeShop.util.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeDBRepo employeeRepo;

    @Autowired
    private ManagerDBRepo managerDBRepo;

    public Employee save(Employee employee) {

        if (employee.getLocation() != null) {
            log.error("Location has to be set to null when creating a new Employee");
            throw new IllegalArgumentException("Location has to be set to null when creating a new Employee");
        }

        if (!Validators.validateName(employee.getFirstName())) {
            log.error("Invalid name");
            throw new IllegalArgumentException("Invalid name");
        }

        // Builderpattern creational pattern
        // if title is Manager, then the employee will be stored as a Manager
        if (employee.getTitle().equals("manager")) {
            Manager newManager = new ManagerBuilder()
                    .withFirstName(employee.getFirstName())
                    .withLastName(employee.getLastName())
                    .withAddress(employee.getAddress())
                    .withSalary(employee.getSalary())
                    .withLocation(null)
                    .buildForCreate();

            managerDBRepo.save(newManager);
            return employee;
        }

        return employeeRepo.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }

    public Employee findById(int id) {
        return employeeRepo.findById((long) id).orElse(null);
    }

    public void delete(int id) {
        if (employeeRepo.existsById((long) id)) {
            employeeRepo.delete(findById(id));
        } else throw new IllegalArgumentException("Employee doesnt exist");
    }
}
