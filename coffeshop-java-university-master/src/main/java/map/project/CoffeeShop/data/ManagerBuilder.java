package map.project.CoffeeShop.data;

import map.project.CoffeeShop.data.model.Location;
import map.project.CoffeeShop.data.model.Manager;

public class ManagerBuilder {
    private int id;

    private Location location;
    private String firstName;
    private String lastName;
    private String address;
    private float salary;

    public ManagerBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public ManagerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ManagerBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ManagerBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public ManagerBuilder withSalary(float salary) {
        this.salary = salary;
        return this;
    }

    public ManagerBuilder withLocation(Location location) {
        this.location = location;
        return this;
    }

    public Manager buildForCreate() {
        Manager m = new Manager();
        m.setId(0);
        m.setLocation(location);
        m.setAddress(address);
        m.setFirstName(firstName);
        m.setLastName(lastName);
        m.setSalary(salary);

        return m;
    }
}
