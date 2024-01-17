package map.project.CoffeeShop.data;

import map.project.CoffeeShop.data.model.OnlineOrder;
import map.project.CoffeeShop.data.model.Order;
import map.project.CoffeeShop.data.model.OrderData;

public class OrderFactory {

    public static Order createOrder(OrderData orderData) {
        if (orderData.getDeliveryAddress() == null || orderData.getDeliveryMan() == null) {
            Order order = new Order();
            order.setId(orderData.getId());
            order.setCustomer(orderData.getCustomer());
            order.setLocation(orderData.getLocation());
            order.setDate_time(orderData.getDateTime());
            return order;
        }

        if (!orderData.getDeliveryAddress().isEmpty() && !orderData.getDeliveryMan().isEmpty()) {
            OnlineOrder onlineOrder = new OnlineOrder();
            onlineOrder.setId(orderData.getId());
            onlineOrder.setCustomer(orderData.getCustomer());
            onlineOrder.setLocation(orderData.getLocation());
            onlineOrder.setDate_time(orderData.getDateTime());
            onlineOrder.setDelivery_man(orderData.getDeliveryMan());
            onlineOrder.setDelivery_address(orderData.getDeliveryAddress());
            return onlineOrder;
        } else {
            Order order = new Order();
            order.setId(orderData.getId());
            order.setCustomer(orderData.getCustomer());
            order.setLocation(orderData.getLocation());
            order.setDate_time(orderData.getDateTime());
            return order;
        }
    }

}
