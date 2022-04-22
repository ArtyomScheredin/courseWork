package spbstu.deans_office.controllers;

import spbstu.deans_office.DTO.AuthRequest;
import spbstu.deans_office.repositories.MyUserRepository;
import spbstu.deans_office.security.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, MyUserRepository myUserRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody AuthRequest request){
        try{
            String name = request.getUserName();
            String token = jwtTokenProvider.createToken(name, myUserRepository
                            .findUserByUserName(name)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found"))
                            .getRoles());
            System.out.println(passwordEncoder.encode(request.getPassword()));
            System.out.println(myUserRepository.findUserByUserName(name).get().getPassword());
        Map<Object, Object> model = new HashMap<>();
        model.put("userName", name);
        model.put("token", token);
        return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
