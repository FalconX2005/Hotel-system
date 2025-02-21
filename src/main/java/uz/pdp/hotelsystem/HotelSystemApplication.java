package uz.pdp.hotelsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "uz.pdp")
public class HotelSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelSystemApplication.class, args);
    }

}
