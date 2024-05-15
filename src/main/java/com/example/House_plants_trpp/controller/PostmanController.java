package com.example.House_plants_trpp.controller;


import com.example.House_plants_trpp.entity.Customer;
import com.example.House_plants_trpp.entity.Order;
import com.example.House_plants_trpp.entity.Product;
import com.example.House_plants_trpp.service.CustomerService;
import com.example.House_plants_trpp.service.OrderService;
import com.example.House_plants_trpp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * Класс представляет собой контроллер.
 * Обрабатывает запросы с Postman.
 */
@Controller
@AllArgsConstructor
public class PostmanController {

    private CustomerService customerService;
    private OrderService orderService;
    private ProductService productService;
    /**
     * Метод получения всех пользователей в формате JSON
     * @return все пользователи
     */
    @GetMapping(path = "/get_customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customers = customerService.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
    /**
     * Метод получения всех заказов в формате JSON
     * @return все заказы
     */
    @GetMapping(path = "/get_orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
    /**
     * Метод получения всех позиций в формате JSON
     * @return все позиции
     */
    @GetMapping(path = "/get_positions")
    public ResponseEntity<List<Product>> getPostions() {
        List<Product> positions = productService.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(positions);
    }
    /**
     * Метод добавления пользователя в формате JSON
     * @param customer новый пользователь
     * @return сообщение
     */
    @PostMapping(path = "/add_customer")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            customerService.create(customer.getName(),
                    customer.getPassword(),
                    customer.getRoles(),
                    customer.getTel(),
                    customer.getAddress());
            return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь успешно создан");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    /**
     * Метод добавления заказа в формате JSON
     * @param orderAndUsername пара из заказа и пользователя, который заказывает
     * @return сообщение
     */
    @PostMapping(path = "/add_order")
    public ResponseEntity<String> createOrder(@RequestBody List<Object> orderAndUsername) {
        try {
            String username = (String) orderAndUsername.get(1);
            Long id = customerService.getIDByName(username);
            Map<String, String> orderData = (Map<String, String>) orderAndUsername.get(0);
            String amount = orderData.get("amount");
            String product_set = orderData.get("product_set");
            System.out.println(amount);
            System.out.println(product_set);
            System.out.println(id);
            orderService.create(amount, product_set, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Заказ успешно создан");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    /**
     * Метод добавления позиции в формате JSON
     * @param product новая позиция
     * @return сообщение
     */
    @PostMapping(path = "/add_position")
    public ResponseEntity<String> createPosition(@RequestBody Product product) {
        try {
            productService.create(product.getName(),
                    product.getPrice());
            return ResponseEntity.status(HttpStatus.CREATED).body("Позиция успешно создана");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
