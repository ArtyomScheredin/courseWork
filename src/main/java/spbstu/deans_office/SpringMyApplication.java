package spbstu.deans_office;

import spbstu.deans_office.security.JwtProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class SpringMyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringMyApplication.class, args);
    }
}
