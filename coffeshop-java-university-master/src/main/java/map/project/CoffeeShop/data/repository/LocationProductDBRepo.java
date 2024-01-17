package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.LocationProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationProductDBRepo extends JpaRepository<LocationProduct, Integer> {
}
