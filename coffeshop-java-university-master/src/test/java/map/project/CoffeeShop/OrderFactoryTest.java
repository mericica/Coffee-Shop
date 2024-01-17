package map.project.CoffeeShop;

import map.project.CoffeeShop.data.ManagerBuilder;
import map.project.CoffeeShop.data.OrderFactory;
import map.project.CoffeeShop.data.model.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OrderFactoryTest {

    @Test
    void testCreateOrder() {
        OrderData orderData = new OrderData();

        orderData.setId(2);

        Customer customer = new Customer();
        customer.setId(3);
        customer.setFirstName("bob");
        orderData.setCustomer(customer);

        Location l = new Location();
        l.setId(3);
        l.setActive(true);
        orderData.setLocation(l);

        Order returnedOrder = OrderFactory.createOrder(orderData);
        assertFalse(returnedOrder instanceof OnlineOrder);
        assertEquals(orderData.getId(),returnedOrder.getId());
        assertEquals(l.getId(), returnedOrder.getLocation().getId());
        assertEquals(l.isActive(), returnedOrder.getLocation().isActive());
        assertEquals(customer.getId(),returnedOrder.getCustomer().getId());
        assertEquals(customer.getFirstName(),returnedOrder.getCustomer().getFirstName());
    }

    @Test
    void testCreateOnlineOrder() {
        OrderData orderData = new OrderData();

        orderData.setDeliveryMan("dob");
        orderData.setDeliveryAddress("testaddr");

        orderData.setId(2);

        Customer customer = new Customer();
        customer.setId(3);
        customer.setFirstName("bob");
        orderData.setCustomer(customer);

        Location l = new Location();
        l.setId(3);
        l.setActive(true);
        orderData.setLocation(l);

        Order returnedOrdertemp = OrderFactory.createOrder(orderData);
        assert(returnedOrdertemp instanceof OnlineOrder);
        OnlineOrder returnedOrder = (OnlineOrder) returnedOrdertemp;

        assertEquals(orderData.getId(),returnedOrder.getId());
        assertEquals(l.getId(), returnedOrder.getLocation().getId());
        assertEquals(l.isActive(), returnedOrder.getLocation().isActive());
        assertEquals(customer.getId(),returnedOrder.getCustomer().getId());
        assertEquals(customer.getFirstName(),returnedOrder.getCustomer().getFirstName());
        assert(returnedOrder.getDelivery_address().equals("testaddr"));
        assert(returnedOrder.getDelivery_man().equals("dob"));

    }
}