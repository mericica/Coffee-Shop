package map.project.CoffeeShop;

import map.project.CoffeeShop.data.model.Customer;
import map.project.CoffeeShop.data.repository.CustomerInMemoryRepo;
import map.project.CoffeeShop.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

public class CustomerServiceTest {
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        // Initialize CustomerService before each test
        customerService = new CustomerService(new CustomerInMemoryRepo());
    }

    @Test
    void testAddCustomerExceptions(){
        Customer customer = new Customer();
        customer.setId(3);
        customer.setFirstName("");
        customer.setLastName("Svi");
        customer.setAddress("testaddr");

        try{
            customerService.save(customer);
            assert(false);
        } catch (Exception e){
            assert e.getMessage().equals("Invalid first name");
        }
        customer.setFirstName("David");
        customer.setLastName("");

        try{
            customerService.save(customer);
            assert(false);
        } catch (Exception e){
            assert e.getMessage().equals("Invalid last name");
        }
        customer.setLastName("Svi");
        customer.setAddress("");

        try{
            customerService.save(customer);
            assert(false);
        } catch (Exception e){
            assert e.getMessage().equals("Invalid address");
        }
    }

    @Test
    void testAddCustomer(){
        Customer customer = new Customer();
        customer.setId(3);
        customer.setFirstName("Bob");
        customer.setLastName("D");
        customer.setAddress("testaddr");

        Customer savedCustomer = customerService.save(customer);
        assert(savedCustomer.equals(customer));
    }

    @Test
    void testFindByIdCustomer(){
        Customer customer = new Customer();
        customer.setId(3);
        customer.setFirstName("Bob");
        customer.setLastName("D");
        customer.setAddress("testaddr");

        Customer savedCustomer = customerService.save(customer);
        assert(savedCustomer.equals(customer));

        Optional<Customer> foundCustomer = customerService.getByID(2);

        assert(foundCustomer.isEmpty());

        foundCustomer = customerService.getByID(1); //because the id is associated automatically to each tuple
        assert(foundCustomer.isPresent());
        savedCustomer = foundCustomer.get();

        assert(savedCustomer.equals(customer));
    }

    @Test
    void testDeleteCustomer(){
        Customer customer = new Customer();
        customer.setId(3);
        customer.setFirstName("Bob");
        customer.setLastName("D");
        customer.setAddress("testaddr");

        Customer savedCustomer = customerService.save(customer);
        assert(savedCustomer.equals(customer));

        try{
            customerService.deleteById(2);
            assert(false);
        } catch (Exception e) {
            assert(e.getMessage().equals("Customer not found with id: " + 2));
        }

        Optional<Customer> foundCustomer = customerService.getByID(1); //because the id is associated automatically to each tuple
        assert(foundCustomer.isPresent());

        customerService.deleteById(1);

        foundCustomer = customerService.getByID(1); //because the id is associated automatically to each tuple
        assert(foundCustomer.isEmpty());
    }
}
