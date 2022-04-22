package spbstu.deans_office;

import spbstu.deans_office.models.MyUser;
import spbstu.deans_office.repositories.MyUserRepository;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestDataInit implements CommandLineRunner {

    @Autowired
    MyUserRepository myUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

//        myUserRepository.save(new MyUser("user",
//                passwordEncoder.encode("123"),
//                Collections.singletonList("ROLE_USER")));
//        myUserRepository.save(new MyUser("admin",
//                passwordEncoder.encode("123"),
//                Collections.singletonList("ROLE_ADMIN")));
    }
}
