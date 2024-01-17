package map.project.CoffeeShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeShopApplication.class, args);
    }


	/*@Bean
	public CustomerService customerServiceDB(CustomerDBRepo customerRepository) {
		return new CustomerService(customerRepository);
	}

	 */

}
