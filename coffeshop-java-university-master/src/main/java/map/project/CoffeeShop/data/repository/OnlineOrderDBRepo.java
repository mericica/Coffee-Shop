package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.OnlineOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnlineOrderDBRepo extends JpaRepository<OnlineOrder, Integer> {
}
