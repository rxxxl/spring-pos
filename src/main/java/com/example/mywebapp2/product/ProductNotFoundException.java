package com.example.mywebapp2.product;

public class ProductNotFoundException extends Throwable {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
