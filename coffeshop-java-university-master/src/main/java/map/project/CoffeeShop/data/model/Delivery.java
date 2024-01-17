package map.project.CoffeeShop.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
//@ToString(exclude = "post")
//@EqualsAndHashCode(exclude = "post")
@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Location location;

    private String supplier;
    private String supplierAdress;
    private String date;
    private float totalCost;


}
