package map.project.CoffeeShop.data.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("OnlineOrder")
@Data
public class OnlineOrder extends Order {

    /*
    public OnlineOrder(OrderData orderData) {
        super(orderData);
        this.delivery_address = orderData.;
    }
     */

    private String delivery_address;
    private String delivery_man;
}
