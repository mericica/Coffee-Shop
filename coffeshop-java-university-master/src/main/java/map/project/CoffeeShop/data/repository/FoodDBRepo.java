package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDBRepo extends JpaRepository<Product, Long> {
}
