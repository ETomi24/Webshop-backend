package org.ewebshop.user;

import org.ewebshop.user.security.UserWebSecurityConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@EnableFeignClients(
        basePackages = "org.ewebshop.commons.clients"
)
@EnableEurekaClient
@SpringBootApplication( scanBasePackages = {
        "org.ewebshop.commons.security",
        "org.ewebshop.user"
})
@Import(UserWebSecurityConfig.class)
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> userService.addAtStart("Admin", "Admin", Role.ADMIN);
    }
}
