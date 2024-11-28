package com.ecommerce.order.domain.exception;

public class EndpointInPropertiesNotFound extends RuntimeException {
    public EndpointInPropertiesNotFound(String endpointName) {
        super("The endpoint " + endpointName + " was not found in properties.");
    }
}
