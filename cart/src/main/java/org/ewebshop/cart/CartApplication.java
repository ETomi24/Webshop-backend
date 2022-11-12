package org.ewebshop.cart;

import org.ewebshop.cart.security.CartWebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@EnableFeignClients(
        basePackages = "org.ewebshop.commons.clients"
)
@EnableEurekaClient
@SpringBootApplication( scanBasePackages = {
        "org.ewebshop.commons.security",
        "org.ewebshop.cart"
})
@Import(CartWebSecurityConfig.class)
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}
