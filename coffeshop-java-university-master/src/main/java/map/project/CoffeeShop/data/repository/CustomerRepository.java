package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);

    List<Customer> findAll();

    Optional<Customer> findById(int id);

    void deleteById(int id);

}
