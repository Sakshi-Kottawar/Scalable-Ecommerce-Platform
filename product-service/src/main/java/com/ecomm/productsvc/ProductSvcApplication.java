package com.ecomm.productsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProductSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductSvcApplication.class, args);
	}

}
