package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDBRepo extends JpaRepository<Event, Long> {
}
