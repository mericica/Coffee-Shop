package map.project.CoffeeShop.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
//@ToString(exclude = "post")
//@EqualsAndHashCode(exclude = "post")
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "locationId")
    @JsonIgnoreProperties({"manager", "orders", "employees", "events"})
    private Location location;

    private String name;
    private String host;
    private float profit;

}
