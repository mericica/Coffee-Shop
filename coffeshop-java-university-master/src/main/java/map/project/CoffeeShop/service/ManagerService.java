package map.project.CoffeeShop.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import map.project.CoffeeShop.data.model.Manager;
import map.project.CoffeeShop.data.repository.ManagerDBRepo;
import map.project.CoffeeShop.util.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ManagerService implements ManagerServiceInterface {

    @Autowired
    private ManagerDBRepo managerRepo;

    public Manager save(Manager manager) {

        if (!Validators.validateName(manager.getFirstName())) {
            log.error("Invalid name");
            throw new IllegalArgumentException("Invalid name");
        }

        return managerRepo.save(manager);
    }

    public List<Manager> getAll() {
        return managerRepo.findAll();
    }

    public Manager findById(int id) {
        return managerRepo.findById((long) id).orElse(null);
    }

    public void delete(int id) {
        if (managerRepo.existsById((long) id)) {
            managerRepo.delete(findById(id));
        } else throw new IllegalArgumentException("Manager doesnt exist");
    }
}
