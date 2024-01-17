package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.OrderFactory;
import map.project.CoffeeShop.data.model.OnlineOrder;
import map.project.CoffeeShop.data.model.Order;
import map.project.CoffeeShop.data.model.OrderData;
import map.project.CoffeeShop.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/createOrder")
public class OnlineOrSimpleOrderCreateController {
    private final OrderController orderController;

    private final OnlineOrderController onlineOrderController;

    private final OrderProductService orderProductController;

    @Autowired
    public OnlineOrSimpleOrderCreateController(OrderController orderController, OnlineOrderController onlineOrderController, OrderProductService orderProductController) {
        this.orderController = orderController;
        this.onlineOrderController = onlineOrderController;
        this.orderProductController = orderProductController;
    }

    @PostMapping("")
    public OrderData create(@RequestBody OrderData orderData) {

        Order order = OrderFactory.createOrder(orderData);

        if (order instanceof OnlineOrder) {
            return orderData;
        } else {
            if (orderController.create(order).isPresent())
                return orderData;
        }
        return null;
    }


}
