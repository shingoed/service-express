package com.rachnicrice.spordering;

import com.rachnicrice.spordering.models.Product;
import com.rachnicrice.spordering.models.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpOrderingApplication {

	@Autowired
	ProductRepository prodRepo;

	public static void main(String[] args) {
//		prodRepo.save(new Product('DS2X310BLK-LF', 'Downspout', '2x3', '10', 'Black', 'stylin', 'aluminum', 10));


		SpringApplication.run(SpOrderingApplication.class, args);
	}

}
