package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductDBRepo extends JpaRepository<OrderProduct, Integer> {
}
