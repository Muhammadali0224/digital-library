package uz.pdp.digitallibrary.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.digitallibrary.enums.Role;
import uz.pdp.digitallibrary.entity.User;
import uz.pdp.digitallibrary.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.role}")
    private String adminRole;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername(adminUsername).isEmpty()) {
            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(Role.valueOf(adminRole));
            userRepository.save(admin);
            System.out.println("Admin user created with username: " + adminUsername);
        } else {
            System.out.println("Admin user already exists");
        }
    }
}

