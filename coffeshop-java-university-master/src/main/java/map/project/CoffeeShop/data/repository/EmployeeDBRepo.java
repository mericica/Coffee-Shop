package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDBRepo extends JpaRepository<Employee, Long> {
}
