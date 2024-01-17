package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDBRepo extends JpaRepository<Order, Integer> {
}
