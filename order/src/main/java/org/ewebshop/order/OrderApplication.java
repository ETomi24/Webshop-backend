package org.ewebshop.order;

import org.ewebshop.order.security.OrderWebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;


@EnableEurekaClient
@EnableFeignClients (
        basePackages = "org.ewebshop.commons.clients"
)
@SpringBootApplication( scanBasePackages = {
        "org.ewebshop.commons.security",
        "org.ewebshop.order"
})
@Import(OrderWebSecurityConfig.class)
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
