package spbstu.deans_office;

import spbstu.deans_office.models.User;
import spbstu.deans_office.repositories.MarkRepository;
import spbstu.deans_office.repositories.UserRepository;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestDataInit implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("user",
                passwordEncoder.encode("123"),
                Collections.singletonList("ROLE_USER")));
        userRepository.save(new User("admin",
                passwordEncoder.encode("123"),
                Collections.singletonList("ROLE_ADMIN")));
    }
}
