package map.project.CoffeeShop.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "order_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "orders")
public class Order {
    /*
        public Order() {}
        public Order(OrderData orderData) {
            this.id = orderData.getId();
            this.date_time = orderData.getDateTime();
            this.location = orderData.getLocation();
            this.customer = orderData.getCustomer();
        }
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date_time;

    @ManyToOne
    @JoinColumn(name = "location")
    @JsonIgnoreProperties({"orders", "locationProducts"})
    private Location location;

    @ManyToOne
    @JoinColumn(name = "customer")
    @JsonIgnoreProperties("orders")
    private Customer customer;

    @OneToMany(targetEntity = map.project.CoffeeShop.data.model.OrderProduct.class, cascade = ALL,
            mappedBy = "order")
    @JsonIgnoreProperties("order")
    private List<OrderProduct> orderProducts;

}
