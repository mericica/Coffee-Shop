package map.project.CoffeeShop.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "locationId")
    @JsonIgnoreProperties("employees") //to not create an infinite loop while serializing to JSON
    private Location location;

    private String firstName;
    private String lastName;
    private String address;
    private float salary;
    private String title;


}
