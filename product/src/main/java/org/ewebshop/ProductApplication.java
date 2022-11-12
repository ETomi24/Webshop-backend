package org.ewebshop;

import org.ewebshop.security.ProductServiceWebSecurityConfig;
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
        "org.ewebshop.product",
        "org.ewebshop.category",
        "org.ewebshop.security"
})
@Import(ProductServiceWebSecurityConfig.class)
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}