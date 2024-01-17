package map.project.CoffeeShop.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import map.project.CoffeeShop.data.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class SpecialManagerService implements ManagerServiceInterface {

    @Autowired
    private ManagerService managerService;

    @Override
    public Manager save(Manager manager) {
        if (manager.getSalary() > 10000)
            return managerService.save(manager);
        else return null;
    }

    @Override
    public List<Manager> getAll() {
        return managerService.getAll();
    }

    @Override
    public Manager findById(int id) {
        return managerService.findById(id);
    }

    @Override
    public void delete(int id) {
        managerService.delete(id);
    }
}
