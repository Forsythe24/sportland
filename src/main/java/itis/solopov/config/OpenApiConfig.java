package itis.solopov.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("mironsolopov24@gmail.com");
        contact.setName("Miron Solopov");
        contact.setUrl("https://t.me/ForsyTheGreat");

        License kfuLicense = new License().name("KFU ITIS").url("https://kpfu.ru");

        Info info = new Info()
                .title("Find Your Perfect Sport Instructor API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage information about sport instructors and their clients")
                .license(kfuLicense);
        return new OpenAPI().info(info);
    }
}