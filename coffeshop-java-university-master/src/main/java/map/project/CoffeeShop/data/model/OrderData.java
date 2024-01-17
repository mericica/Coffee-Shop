package map.project.CoffeeShop.data.model;

import lombok.Data;

@Data
public class OrderData {
    private int id;
    private String dateTime;
    private Location location;
    private Customer customer;
    private String deliveryAddress;
    private String deliveryMan;
}
