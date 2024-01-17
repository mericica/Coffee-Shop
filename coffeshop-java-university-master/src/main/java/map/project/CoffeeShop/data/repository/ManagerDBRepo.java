package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerDBRepo extends JpaRepository<Manager, Long> {

}
