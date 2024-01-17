package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.model.Product;
import map.project.CoffeeShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        //System.out.println(student.getName()+Integer.toString(student.getAge()));
        return productService.save(product);
    }

    @GetMapping("/food/all")
    public List<Product> getAllFood() {
        return productService.getAllFood();
    }

    @GetMapping("/drinks/all")
    public List<Product> getAllDrinks() {
        return productService.getAllDrinks();
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/drinks/sortByPrice/asc")
    public List<Product> sortDrinksByPriceASC() {
        return productService.sortDrinksByPriceASC();
    }

    @GetMapping("/food/sortByPrice/asc")
    public List<Product> sortFoodByPriceASC() {
        return productService.sortFoodByPriceASC();
    }

    @GetMapping("/drinks/sortByPrice/desc")
    public List<Product> sortDrinksByPriceDESC() {
        return productService.sortDrinksByPriceDESC();
    }

    @GetMapping("/food/sortByPrice/desc")
    public List<Product> sortFoodByPriceDESC() {
        return productService.sortFoodByPriceDESC();
    }

    @GetMapping(path = "/{id}")
    public Product getByID(@PathVariable("id") int id) {
        return productService.getByID(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodById(@PathVariable int id) {
        productService.deleteById(id);
    }


}