package itis.solopov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

    public Application(FreeMarkerConfigurer configurer) {
        configurer.getTaglibFactory().setClasspathTlds(Arrays.asList("/META-INF/security.tld"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
