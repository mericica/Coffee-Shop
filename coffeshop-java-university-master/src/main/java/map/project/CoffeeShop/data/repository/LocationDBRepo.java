package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationDBRepo extends JpaRepository<Location, Long> {
}
