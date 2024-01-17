package map.project.CoffeeShop.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import map.project.CoffeeShop.data.model.OnlineOrder;
import map.project.CoffeeShop.data.repository.OnlineOrderDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class OnlineOrderService {
    @Autowired
    private OnlineOrderDBRepo onlineOrderDBRepo;

    public Optional<OnlineOrder> save(OnlineOrder order) {
        //TODO Validate Date-time!!!!! + Location + Customer !!!!
        return Optional.of(onlineOrderDBRepo.save(order));
    }

    public List<OnlineOrder> findAll() {
        return onlineOrderDBRepo.findAll();
    }

    public Optional<OnlineOrder> getByID(int id) {
        if (id <= 0) {
            log.error("Invalid ID");
            throw new IllegalArgumentException("Invalid ID");
        }
        return onlineOrderDBRepo.findById(id);

    }

    public void deleteById(int id) {
        Optional<OnlineOrder> customer = onlineOrderDBRepo.findById(id);
        if (customer.isPresent()) {
            onlineOrderDBRepo.deleteById(id);
        } else {
            log.error("Customer not found with id: {}", id);
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
    }
}
