package map.project.CoffeeShop.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import map.project.CoffeeShop.data.model.Employee;
import map.project.CoffeeShop.data.model.Location;
import map.project.CoffeeShop.data.model.Manager;
import map.project.CoffeeShop.data.repository.EmployeeDBRepo;
import map.project.CoffeeShop.data.repository.LocationDBRepo;
import map.project.CoffeeShop.data.repository.ManagerDBRepo;
import map.project.CoffeeShop.util.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class LocationService {

    @Autowired
    private LocationDBRepo locationRepo;

    @Autowired
    private EmployeeDBRepo employeeRepo;

    @Autowired
    private ManagerDBRepo managerRepo;

    public Location save(Location location) {

        if (!Validators.validateName(location.getName())) {
            log.error("Invalid name");
            throw new IllegalArgumentException("Invalid name");
        }
        return locationRepo.save(location);
    }

    public Location addEmployeeToLocation(int locationId, int employeeId) {
        // Fetch the Location and Employee entities from their respective repositories
        Location location = locationRepo.findById((long) locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        Employee employee = employeeRepo.findById((long) employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        // Assign the Employee to the Location
        employee.setLocation(location);

        // Add the Employee to the Location's employee list and save the changes
        location.getEmployees().add(employee);
        locationRepo.save(location);
        employeeRepo.save(employee);

        return location;
    }

    //remove employee from location
    public Location removeEmployeeFromLocation(int locationId, int employeeId) {
        // Fetch the Location and Employee entities from their respective repositories
        Location location = locationRepo.findById((long) locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        // Add the Employee to the Location's employee list and save the changes
        List<Employee> employees = location.getEmployees();
        if (!employees.removeIf(e -> e.getId() == employeeId))
            throw new IllegalArgumentException("employee not employed at specified location");

        Employee employee = employeeRepo.findById((long) employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        // Assign the Employee to the Location
        employee.setLocation(null);
        employeeRepo.save(employee);

        return locationRepo.save(location);
    }

    public Location setManagerToLocation(int locationId, int managerId) {
        // Fetch the Location and Employee entities from their respective repositories
        Location location = locationRepo.findById((long) locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        Manager manager = managerRepo.findById((long) managerId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        // Assign the Employee to the Location
        manager.setLocation(location);

        // Add the Employee to the Location's employee list and save the changes
        location.setManager(manager);

        managerRepo.save(manager);
        locationRepo.save(location);

        return location;
    }

    public Location removeManagerFromLocation(int locationId) {
        // Fetch the Location and Employee entities from their respective repositories
        Location location = locationRepo.findById((long) locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        Manager manager = location.getManager();

        if (manager == null) {
            return location;
        }

        // Assign the Employee to the Location
        manager.setLocation(null);

        // Add the Employee to the Location's employee list and save the changes
        location.setManager(null);

        managerRepo.save(manager);
        locationRepo.save(location);

        return location;
    }

    //closelocation
    public Location closeLocation(int locationId) {
        // Fetch the Location and Employee entities from their respective repositories
        Location location = locationRepo.findById((long) locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        location.setActive(false);

        //TD +feature kick out all employees

        return locationRepo.save(location);
    }

    public Location findLocationById(int id) {
        Location location = locationRepo.findById((long) id)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));
        return location;
    }


    public void delete(int id) {
        locationRepo.deleteById(Long.valueOf(id));
    }

    public List<Location> findAll() {
        return locationRepo.findAll();
    }

}
