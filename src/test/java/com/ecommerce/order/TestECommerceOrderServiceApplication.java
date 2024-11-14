package com.ecommerce.order;

import org.springframework.boot.SpringApplication;

public class TestECommerceOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ECommerceOrderServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
