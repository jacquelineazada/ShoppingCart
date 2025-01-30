package com.example.shoppingcart.controller;


import com.example.shoppingcart.dto.SalesOrder;
import com.example.shoppingcart.model.Order;
import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.OrderRepository;
import com.example.shoppingcart.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class OrderController {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<SalesOrder>> getAllOrdersWithProducts() {
        List<SalesOrder> ordersWithProducts = orderRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Order::getCustomer_id)) // Group by customerId
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey)) // Sort by customerId
                .map(entry -> {
                    Long customerId = entry.getKey();
                    List<Order> orders = entry.getValue();

                    // Mapping orders for a particular customer
                    List<SalesOrder.OrderDetails> orderDetailsList = orders.stream()
                            .map(order -> {
                                // Mapping each order to a response object
                                return new SalesOrder.OrderDetails(
                                        order.getOrder_id(),
                                        order.getStatus(),
                                        order.getOrderDate(),
                                        new SalesOrder.ProductDetail(
                                                order.getProduct().getProduct_id(),
                                                order.getProduct().getProduct_name(),
                                                order.getProduct().getProduct_brand(),
                                                order.getProduct().getProduct_category(),
                                                order.getProduct().getPrice().toString()
                                        )
                                );
                            })
                            .collect(Collectors.toList());

                    // Creating the response object for a customer
                    SalesOrder response = new SalesOrder();
                    response.setCustomerId(customerId);
                    response.setOrders(orderDetailsList);

                    return response;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(ordersWithProducts);
    }


    // Purchase Item
    @PostMapping("/purchase/{customer_id}/{id}")
    public ResponseEntity<Order> purchaseItem(@PathVariable Long customer_id, @PathVariable Long id) {
        // Find the product by ID
        Optional<Product> productOpt = productRepository.findById(id);

        if (productOpt.isEmpty()) {
            // Return 404 if product is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Product product = productOpt.get();

        // Create a new order
        Order newOrder = new Order();
        newOrder.setProduct(product);
        newOrder.setCustomer_id(customer_id);
        newOrder.setStatus("Pending"); // Default status
        newOrder.setOrderDate(LocalDateTime.now()); // Set current date

        try {
            // Save the order
           Order savedOrder = orderRepository.save(newOrder);

            // Return 201 Created when the order is successfully saved
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);

        } catch (Exception e) {
            // Return 500 Internal Server Error if something goes wrong with the order
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
