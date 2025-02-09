package org.example.my_project.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.extern.slf4j.Slf4j;
import org.example.my_project.entities.Role;
import org.example.my_project.entities.User;

import org.example.my_project.repository.RoleRepository;
import org.example.my_project.repository.UserRepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("ADMIN") == null) {
                Role role = Role.builder().name("ADMIN")
                        .description("Administrator role")
//                        .permissions(new HashSet<>())
                        .build();
                roleRepository.save(role);
                log.info("Role ADMIN has been created.");
            }


            if (userRepository.findByUsername("admin").isEmpty()) {
                Role adminRole = roleRepository.findByName("ADMIN");
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("1234"))
                        .roles(Set.of(adminRole))
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password is: 1234, please change it");
            }
        };
    }

}
