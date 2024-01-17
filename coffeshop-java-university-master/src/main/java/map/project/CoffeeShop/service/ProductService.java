package map.project.CoffeeShop.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import map.project.CoffeeShop.data.model.Product;
import map.project.CoffeeShop.data.repository.ProductDBRepo;
import map.project.CoffeeShop.util.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductDBRepo productRepo;

    public Product save(Product product) {

        if (product.getPrice() < 0) {
            log.error("Price of Product is negative");
            throw new IllegalArgumentException("Price of Product is negative");
        }

        if (product.getSize() < 0) {
            log.error("Size of Product is negative");
            throw new IllegalArgumentException("Price of Product is negative");
        }

        if (!Validators.validateName(product.getName())) {
            log.error("Invalid name");
            throw new IllegalArgumentException("Invalid name");
        }

        return productRepo.save(product);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public List<Product> getAllFood() {
        return productRepo.findAll().stream().filter(x -> x.getUnit().equals("g"))
                .collect(Collectors.toList());
    }

    public List<Product> getAllDrinks() {
        return productRepo.findAll().stream().filter(x -> x.getUnit().equals("ml"))
                .collect(Collectors.toList());
    }

    public List<Product> sortDrinksByPriceASC() {
        return getAllDrinks()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }

    public List<Product> sortFoodByPriceASC() {
        return getAllFood()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }

    public List<Product> sortDrinksByPriceDESC() {
        return getAllDrinks()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public List<Product> sortFoodByPriceDESC() {
        return getAllFood()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }


    public Product getByID(int id) {
        if (id <= 0) {
            log.error("Invalid ID");
            throw new IllegalArgumentException("Invalid ID");
        }
        return productRepo.findById(id).get();

    }

    public void deleteById(int id) {
        if (productRepo.findById(id).isPresent()) {
            productRepo.deleteById(id);
        } else {
            log.error("Product not found with id: {}", id);
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
    }
}
