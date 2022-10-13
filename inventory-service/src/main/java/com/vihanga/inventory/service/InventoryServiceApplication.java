package com.vihanga.inventory.service;

import com.vihanga.inventory.service.model.Inventory;
import com.vihanga.inventory.service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
//		return  args -> {
//			Inventory inventory = new Inventory();
//			inventory.setSkuCode("Brown Mug");
//			inventory.setQuantity(5);
//
//			Inventory inventory1 = new Inventory();
//			inventory1.setSkuCode("Green Mug");
//			inventory1.setQuantity(7);
//
//			inventoryRepository.save(inventory);
//			inventoryRepository.save(inventory1);
//		};
//	}
}
