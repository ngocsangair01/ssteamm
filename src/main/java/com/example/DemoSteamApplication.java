package com.example;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoSteamApplication {

    @Value("${cloudinary.url}")
    private String CLOUDINARY_URL;
    public static void main(String[] args) {
        SpringApplication.run(DemoSteamApplication.class, args);
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
        return cloudinary;
    }
}
