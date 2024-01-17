package map.project.CoffeeShop.service;

import map.project.CoffeeShop.data.model.LocationProduct;
import map.project.CoffeeShop.data.repository.LocationProductDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.shell.command.invocation.InvocableShellMethod.log;

@Service
public class LocationProductService {

    @Autowired
    private LocationProductDBRepo locationProductDBRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private LocationService locationService;

    public void addProductToStock(LocationProduct locationProduct) {
        //TODO: verify quantity
        //find location
        locationService.findAll()
                .stream()
                .filter(x -> x.getId() == locationProduct.getLocation().getId())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No location found"));

        LocationProduct foundLocationProduct = locationProductDBRepo.findAll()
                .stream()
                .filter(lP -> lP.getProduct().getId() == locationProduct.getProduct().getId() && locationProduct.getLocation().getId() == lP.getLocation().getId())
                .findAny()
                .orElse(null);

        if (foundLocationProduct != null)
            foundLocationProduct.setQuantity(foundLocationProduct.getQuantity() + locationProduct.getQuantity());
        else
            foundLocationProduct = locationProduct;

        locationProductDBRepo.save(foundLocationProduct);
    }

    public LocationProduct save(LocationProduct op) {
        return locationProductDBRepo.save(op);
    }

    public List<LocationProduct> findAll() {
        return locationProductDBRepo.findAll();
    }

    public List<LocationProduct> getAllByLocationId(int locationId) {
        return findAll().stream().filter(lp -> lp.getLocation().getId() == locationId).toList();
    }

    public List<LocationProduct> filterByLocationId(int id) {
        return locationProductDBRepo.findAll().stream().filter(o -> o.getLocation().getId() == id).toList();
    }

    public Optional<LocationProduct> getByID(int id) {
        if (id <= 0) {
            log.error("Invalid ID");
            throw new IllegalArgumentException("Invalid ID");
        }
        return locationProductDBRepo.findById(id);

    }

    public void removeByLocationProductId(int locationId, int productId, int quantity) {
        List<LocationProduct> locationProducts = findAll();
        for (LocationProduct locationProduct : locationProducts) {
            if (locationProduct.getProduct().getId() == productId && locationProduct.getLocation().getId() == locationId) {
                if (locationProduct.getQuantity() < quantity)
                    throw new IllegalArgumentException("Entered quantity is greater than the stock quanitity");

                locationProduct.setQuantity(locationProduct.getQuantity() - quantity);

                if (locationProduct.getQuantity() == 0)
                    locationProductDBRepo.delete(locationProduct);
                else {
                    locationProductDBRepo.save(locationProduct);
                }
                return;
            }
        }

        //if there is no product with the specified locationId and productId
        throw new IllegalArgumentException("No product found at location");
    }

    public void deleteById(int id) {
        Optional<LocationProduct> op = locationProductDBRepo.findById(id);
        if (op.isPresent()) {
            locationProductDBRepo.deleteById(id);
        } else {
            log.error("Customer not found with id: {}", id);
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
    }
}
