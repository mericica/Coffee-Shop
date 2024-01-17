package map.project.CoffeeShop.service;

import map.project.CoffeeShop.data.model.Manager;

import java.util.List;

public interface ManagerServiceInterface {
    Manager save(Manager manager);

    List<Manager> getAll();

    Manager findById(int id);

    void delete(int id);
}
