package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.model.Location;
import map.project.CoffeeShop.data.model.LocationProduct;
import map.project.CoffeeShop.service.LocationProductService;
import map.project.CoffeeShop.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;
    private final LocationProductService locationProductService;

    @Autowired
    public LocationController(LocationService locationService, LocationProductService locationProductService) {
        this.locationService = locationService;
        this.locationProductService = locationProductService;
    }

    @PostMapping("/create")
    public Location create(@RequestBody Location location) {
        return locationService.save(location);
    }

    @PutMapping("/addEmployee/{locationId}/{employeeId}")
    public Location addEmployeeToLocation(@PathVariable("locationId") int locationId, @PathVariable("employeeId") int employeeId) {
        return locationService.addEmployeeToLocation(locationId, employeeId);
    }

    @PutMapping("/removeEmployee/{locationId}/{employeeId}")
    public Location removeEmployeeFromLocation(@PathVariable("locationId") int locationId, @PathVariable("employeeId") int employeeId) {
        return locationService.removeEmployeeFromLocation(locationId, employeeId);
    }

    @PutMapping("/setManager/{locationId}/{managerId}")
    public Location setManagerToLocation(@PathVariable("locationId") int locationId, @PathVariable("managerId") int managerId) {
        return locationService.setManagerToLocation(locationId, managerId);
    }

    @PutMapping("/removeManager/{locationId}")
    public Location removeManager(@PathVariable("locationId") int locationId) {
        return locationService.removeManagerFromLocation(locationId);
    }

    @PutMapping("/closeLocation/{locationId}")
    public Location closeLocation(@PathVariable("locationId") int locationId) {
        return locationService.closeLocation(locationId);
    }

    @GetMapping("/{locationId}")
    public Location findLocationById(@PathVariable("locationId") int locationId) {
        return locationService.findLocationById(locationId);
    }

    @GetMapping("/allAvailableProducts/{locationId}")
    public List<LocationProduct> removeProductFromStock(@PathVariable("locationId") int locationId) {
        return locationProductService.getAllByLocationId(locationId);
    }

    @DeleteMapping("/delete/{locationId}")
    public void deleteLocation(@PathVariable("locationId") int locationId) {
        locationService.delete(locationId);
    }

    @PutMapping("/addProductToStock")
    public void addProductToStock(@RequestBody LocationProduct locationProduct) {
        locationProductService.addProductToStock(locationProduct);
    }

    @PostMapping("/removeProductFromStock/{locationId}/{productId}/{quantity}")
    public void removeProductFromStock(@PathVariable("locationId") int locationId, @PathVariable("productId") int productId, @PathVariable("quantity") int quantity) {
        locationProductService.removeByLocationProductId(locationId, productId, quantity);
    }

    @GetMapping("/all")
    public List<Location> showAll() {
        return locationService.findAll();
    }

}