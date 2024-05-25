package itis.solopov;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    private final DispatcherServlet servlet;

    public Application(FreeMarkerConfigurer configurer, DispatcherServlet servlet) {
        this.servlet = servlet;
        configurer.getTaglibFactory().setClasspathTlds(Arrays.asList("/META-INF/security.tld"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner getCommandLineRunner() {
        servlet.setThrowExceptionIfNoHandlerFound(true);
        return args -> {};
    }
}
