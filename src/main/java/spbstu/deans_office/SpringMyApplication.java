package spbstu.deans_office;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringMyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringMyApplication.class, args);
    }
}
