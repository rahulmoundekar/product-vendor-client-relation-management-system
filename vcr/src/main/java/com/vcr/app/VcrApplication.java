package com.vcr.app;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.vcr.app.document.Clients;
import com.vcr.app.document.Products;
import com.vcr.app.repo.impl.ClientRepository;
import com.vcr.app.repo.impl.ProductRepository;

@SpringBootApplication
public class VcrApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(VcrApplication.class, args);
		ClientRepository clientRepository = ac.getBean(ClientRepository.class);
		ProductRepository productRepository = ac.getBean(ProductRepository.class);

		Optional<Products> products = productRepository.findById("5e70969e8f89354ef093b5ba");
		
		  System.out.println(products.get());
		  
		  Clients clients = new Clients(); clients.setClient_id(1);
		  clients.setName("Rahul"); clients.setContact("9158565604");
		  clients.setActive(false); clients.setUpdate_timestamp(1584437872294l);
		  clients.setProductList(products.map(Collections::singleton).orElseGet(
		  Collections::emptySet));
		  
		  clientRepository.save(clients); System.out.println("client Saved");
		 

		clientRepository.findAll().forEach(System.out::println);

	}
}
