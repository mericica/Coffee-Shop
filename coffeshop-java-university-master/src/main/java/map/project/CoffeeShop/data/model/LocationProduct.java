package map.project.CoffeeShop.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;


@Data
//@ToString(exclude = "post")
//@EqualsAndHashCode(exclude = "post")
@Entity
@Table(name = "location_products")
public class LocationProduct {
    //This represents the product stock per location, if this stock is 0 then there are no more Products available at the specific location and you have to refill

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonIgnoreProperties({"orderProducts", "locationProducts"})
    @JsonIgnore
    private Location location;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
}
