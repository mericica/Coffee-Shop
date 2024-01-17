package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerInMemoryRepo implements CustomerRepository {


    private final List<Customer> customers = new ArrayList<Customer>();
    private int nextId = 1;

    @Override
    public Customer save(Customer customer) {
        customer.setId(nextId++);
        customers.add(customer);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Optional<Customer> findById(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) {
        customers.removeIf(customer -> customer.getId() == id);

    }
}
