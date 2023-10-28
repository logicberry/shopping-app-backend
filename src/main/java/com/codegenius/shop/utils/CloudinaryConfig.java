package com.codegenius.shop.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        // Load Cloudinary credentials from environment variables
        Dotenv dotenv = Dotenv.load();
        String cloudinaryUrl = dotenv.get("CLOUDINARY_URL");

        // Create and configure a Cloudinary instance
        Cloudinary cloudinary = new Cloudinary(cloudinaryUrl);

        return cloudinary;
    }
}
