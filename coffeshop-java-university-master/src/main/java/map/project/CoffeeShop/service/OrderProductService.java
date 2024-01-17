package map.project.CoffeeShop.service;

import map.project.CoffeeShop.data.model.LocationProduct;
import map.project.CoffeeShop.data.model.OrderProduct;
import map.project.CoffeeShop.data.repository.OrderProductDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.shell.command.invocation.InvocableShellMethod.log;

@Service
public class OrderProductService {
    @Autowired
    private OrderProductDBRepo orderProductDBRepo;

    @Autowired
    private LocationProductService locationProductService;

    @Autowired
    private ProductService productService;

    public OrderProduct save(OrderProduct op) {

        List<OrderProduct> allOrderProducts = findAll();

        // Verify if there are enough products available at this location and decrease the number of products if possible
        List<LocationProduct> locationProducts = locationProductService.findAll();
        LocationProduct foundLocationProduct = locationProducts.stream()
                .filter(lp ->
                        lp.getProduct().getId() == op.getProduct().getId() && op.getQuantity() <= lp.getQuantity() && op.getOrder().getLocation().getId() == lp.getLocation().getId())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Not enough products available at entered Location/no location found"));

        //subtract the quantity of the locations stock
        int quantityLocationProduct = foundLocationProduct.getQuantity();
        int quantityOrderProduct = op.getQuantity();

        foundLocationProduct.setQuantity(quantityLocationProduct - quantityOrderProduct);
        System.out.println("hier");
        locationProductService.save(foundLocationProduct);

        //verify if there is already an order with this type of product added
        for (OrderProduct op2 : allOrderProducts) {
            if (op2.getProduct().getId() == op.getProduct().getId() && op2.getOrder().getId() == op.getOrder().getId()) {


                op2.setQuantity(op2.getQuantity() + op.getQuantity());
                return orderProductDBRepo.save(op2);
            }
        }

        return orderProductDBRepo.save(op);
    }

    public List<OrderProduct> findAll() {
        return orderProductDBRepo.findAll();
    }

    public List<OrderProduct> filterByOrderId(int id) {
        return orderProductDBRepo.findAll().stream().filter(o -> o.getOrder().getId() == id).toList();
    }

    public Optional<OrderProduct> getByID(int id) {
        if (id <= 0) {
            log.error("Invalid ID");
            throw new IllegalArgumentException("Invalid ID");
        }
        return orderProductDBRepo.findById(id);
    }

    public void deleteById(int id) {
        Optional<OrderProduct> op = orderProductDBRepo.findById(id);
        if (op.isPresent()) {
            orderProductDBRepo.deleteById(id);
        } else {
            log.error("OrderProduct not found with id: {}", id);
            throw new IllegalArgumentException("OrderProduct not found with id: " + id);
        }
    }

    public void deleteByOrderProductId(int orderId, int productId, int quantity) {
        List<OrderProduct> orderProducts = orderProductDBRepo.findAll();

        for (OrderProduct orderProduct : orderProducts) {
            if (orderProduct.getQuantity() < quantity)
                throw new IllegalArgumentException("The preferred quantity you want to remove from the order is higher than the quantity you actually ordered");

            if (orderProduct.getProduct().getId() == productId && orderProduct.getOrder().getId() == orderId) {
                LocationProduct locationProduct = locationProductService
                        .findAll()
                        .stream()
                        .filter(lp -> lp.getProduct().getId() == productId)
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("No locationProduct found to add the removed orderProduct quantity"));

                locationProduct.setQuantity(locationProduct.getQuantity() + quantity);
                orderProduct.setQuantity(orderProduct.getQuantity() - quantity);

                //remove entirely orderProduct if quantity is 0
                if (orderProduct.getQuantity() == 0) orderProductDBRepo.delete(orderProduct);
                else orderProductDBRepo.save(orderProduct);

                locationProductService.save(locationProduct);
                return;
            }
        }
        log.error("OrderProduct not found");
        throw new IllegalArgumentException("OrderProduct not found");
    }
}
