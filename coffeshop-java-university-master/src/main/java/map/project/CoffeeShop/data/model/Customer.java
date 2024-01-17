package map.project.CoffeeShop.data.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String address;
}
