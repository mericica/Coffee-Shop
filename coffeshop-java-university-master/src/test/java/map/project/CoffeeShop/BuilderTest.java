package map.project.CoffeeShop;

import map.project.CoffeeShop.data.ManagerBuilder;
import map.project.CoffeeShop.data.model.Location;
import map.project.CoffeeShop.data.model.Manager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuilderTest {

    @Test
    void testManagerBuilder() {
        int id = 1;
        float salary = 1000.0f;
        String firstName = "Bobby", lastName = "Fischer", address = "Street 1";
        Location location = null;

        Manager manager = new ManagerBuilder()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withAddress(address)
                .withLocation(location)
                .withSalary(salary)
                .buildForCreate();

        assertEquals(manager.getId(), 0);
        assertEquals(manager.getFirstName(),firstName);
        assertEquals(manager.getLastName(), lastName);
        assertEquals(manager.getAddress(), address);
        assertEquals(manager.getSalary(), salary);
        assertEquals(manager.getLocation(), null);
    }
}
