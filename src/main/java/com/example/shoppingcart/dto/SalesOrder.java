package com.example.shoppingcart.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SalesOrder {
    private Long customerId;
    private List<OrderDetails> orders;

    public SalesOrder(Long customerId, List<OrderDetails> orders) {
        this.customerId = customerId;
        this.orders = orders;
    }

    public SalesOrder() {

    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderDetails> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDetails> orders) {
        this.orders = orders;
    }

    // Constructor, Getters, and Setters

    public static class OrderDetails {
        private Long orderId;
        private String status;
        private LocalDateTime orderDate;
        private ProductDetail product;  // Change List<ProductDetail> to ProductDetail

        public OrderDetails() {
        }

        public OrderDetails(Long orderId, String status, LocalDateTime orderDate, ProductDetail product) {
            this.orderId = orderId;
            this.status = status;
            this.orderDate = orderDate;
            this.product = product;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public LocalDateTime getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
        }

        public ProductDetail getProduct() {
            return product;
        }

        public void setProduct(ProductDetail product) {
            this.product = product;
        }
// Constructor, Getters, and Setters

    }

    public static class ProductDetail {
        private Long product_id;
        private String product_name;
        private String product_brand;
        private String product_category;
        private String price;

        // Constructor, Getters, and Setters

        public Long getProduct_id() {
            return product_id;
        }

        public void setProduct_id(Long product_id) {
            this.product_id = product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_brand() {
            return product_brand;
        }

        public void setProduct_brand(String product_brand) {
            this.product_brand = product_brand;
        }

        public String getProduct_category() {
            return product_category;
        }

        public void setProduct_category(String product_category) {
            this.product_category = product_category;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public ProductDetail() {
        }

        public ProductDetail(Long product_id, String product_name, String product_brand, String product_category, String price) {
            this.product_id = product_id;
            this.product_name = product_name;
            this.product_brand = product_brand;
            this.product_category = product_category;
            this.price = price;
        }
    }
}