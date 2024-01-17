package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDBRepo extends JpaRepository<Customer, Integer>, CustomerRepository {
}
