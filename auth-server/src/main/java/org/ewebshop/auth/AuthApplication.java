package org.ewebshop.auth;

import org.ewebshop.commons.security.JwtAuthenticationEntryPoint;
import org.ewebshop.commons.security.JwtRequestFilter;
import org.ewebshop.commons.security.JwtTokenUtil;
import org.ewebshop.commons.security.JwtUserDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@EnableFeignClients(
        basePackages = "org.ewebshop.commons.clients"
)
@EnableEurekaClient
@SpringBootApplication(
        scanBasePackages = {
                "org.ewebshop.commons.security",
                "org.ewebshop.auth"
        }
)
@Import({WebSecurityConfig.class, JwtRequestFilter.class, JwtAuthenticationEntryPoint.class, JwtUserDetailsService.class,JwtTokenUtil.class})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
