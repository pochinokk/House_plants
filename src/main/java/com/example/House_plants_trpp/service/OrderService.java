package com.example.House_plants_trpp.service;

import com.example.House_plants_trpp.entity.Customer;
import com.example.House_plants_trpp.entity.Order;
import com.example.House_plants_trpp.entity.Product;
import com.example.House_plants_trpp.repository.CustomerRepository;
import com.example.House_plants_trpp.repository.OrderRepository;
import com.example.House_plants_trpp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс представляет собой сервис для работы
 * с репозитоием сущности заказа.
 */
@Service
@AllArgsConstructor
public class OrderService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    public void create(String amount, String product_set, Long customer_id) {
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Order order = Order.builder()
                .amount(amount)
                .product_set(product_set)
                .customer(customer)
                .build();
        orderRepository.save(order);
    }
    public boolean exists(Long id) {
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders){
            if (order.getId().equals(id)){
                return true;
            }
        }
        return false;
    }


    public String getOrderAmount(String str) {
        int amount = 0;
        List<List<String>> order_list = new ArrayList<>();
        String[] a = str.split(",\\s*");
        for (String pos : a) {
            String[] b = pos.split("_");
            if (b.length == 2) {
                List<String> pair = new ArrayList<>();
                pair.add(b[0]);
                if (b[1].length() >= 3) {
                    b[1] = b[1].substring(0, b[1].length() - 3);
                }
                pair.add(b[1]);
                order_list.add(pair);
            }
        }
        List<Product> positions = productRepository.findAll();
        for (List<String> pair : order_list) {
            String productName = pair.get(0);
            int quantity = Integer.parseInt(pair.get(1));
            boolean found = false;

            for (Product product : positions) {
                if (product.getName().equals(productName)) {
                    int price = Integer.parseInt(product.getPrice());
                    amount += price * quantity;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return "-1";
            }
        }
        return Integer.toString(amount);
    }
    public List<Order> readAll() {
        return orderRepository.findAll();
    }

    public List<Order> readAllByUserId(Long id) {
        List<Order> orders = orderRepository.findAll();
        List<Order> filtered_orders = new ArrayList<>();
        for(Order order : orders)
        {
            if(order.getCustomer().getId().equals(id))
            {
                filtered_orders.add(order);
            }
        }
        return filtered_orders;
    }

    public Order getOrderByID(Long order_id){
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders){
            if (order.getId().equals(order_id)){
                return order;
            }
        }
        return null;
    }

    public void delete(Long id) {orderRepository.deleteById(id);}
}