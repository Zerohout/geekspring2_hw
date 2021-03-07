package com.geekbrains.geekspring.services;

import com.geekbrains.geekspring.entities.*;
import com.geekbrains.geekspring.repositories.DeliveryAddressRepository;
import com.geekbrains.geekspring.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Status;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderStatusService orderStatusService;
    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderStatusService(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @Autowired
    public void setDeliveryAddressService(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @Transactional
    public Order makeOrder(ShoppingCart cart, User user) {
        Order order = new Order();
        order.setOrderItems(cart.getItems());
        order.setStatus(orderStatusService.getStatusById(1L));
        order.setUser(user);
        DeliveryAddress address = deliveryAddressService.getUserAddresses(user.getId()).stream().findFirst().orElse(null);
        order.setDeliveryAddress(address);
        order.setDeliveryPrice(1D);
        order.setCreatedAt(LocalDateTime.now());
        order.setDeliveryDate(LocalDateTime.now().plusDays(1L));
        order.setPrice(cart.getTotalCost());
        order.setPhoneNumber(user.getPhone());
        return saveOrder(order);
    }

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    public Order saveOrder(Order order) {
        orderRepository.save(order);
        return order;
    }

    public Order changeOrderStatus(Order order, Long statusId) {
        order.setStatus(orderStatusService.getStatusById(statusId));
        return saveOrder(order);
    }
}
