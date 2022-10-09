package com.techvify.userservice;

import com.techvify.userservice.domain.Role;
import com.techvify.userservice.domain.User;
import com.techvify.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
            userService.saveUser(new User(null, "Trung", "nbtrung195", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Tuan", "lexnguyen", "1234", new ArrayList<>()));

            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.addRoleToUser("nbtrung195", "ROLE_ADMIN");
            userService.addRoleToUser("nbtrung195", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("lexnguyen", "ROLE_USER");
        };
    }

}
