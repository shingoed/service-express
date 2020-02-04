package com.rachnicrice.spordering;

import com.rachnicrice.spordering.models.ApplicationUser;
import com.rachnicrice.spordering.models.Product;
import com.rachnicrice.spordering.models.ApplicationUserRepository;
import com.rachnicrice.spordering.models.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product("DS2x3BLK80", "DS 2x3 ", "80", "Black", "DownSpout"));
        productRepository.save(new Product("DS3x3BLU40", "DS 3x3 ", "40", "Blue", "DownSpout"));
        productRepository.save(new Product("DS4x3YEL60", "DS 4x3 ", "60", "Yellow", "DownSpout"));
        productRepository.save(new Product("DS2x3GRN100", "DS 2x3 ", "100", "Green", "DownSpout"));
        productRepository.save(new Product("DS5x2BLK10", "DS 5x2 ", "10", "Black", "DownSpout"));
        productRepository.save(new Product("DS2x380", "DS 2x3 W/ style", "80", "60", "styling", "OtherType"));
        productRepository.save(new Product("DS2x3BLK80", "DS 2x3 ", "80","70", "Black", "Styling", "OtherType"));
        productRepository.save(new Product("DS2x3BLK80", "DS 2x3 W/ style", "180", "260", "Black", "OtherType"));
        productRepository.save(new Product("DS2x3BLK80", "DS 2x3 ", "50","70", "Styling", "OtherType"));
        productRepository.save(new Product("DS2x3BLK80", "DS 2x3 W/ style", "480", "260", "Black", "OtherType"));
//        applicationUserRepository.save(new ApplicationUser("username","password"));

    }

}
