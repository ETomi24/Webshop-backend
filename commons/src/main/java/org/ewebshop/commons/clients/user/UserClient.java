package org.ewebshop.commons.clients.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient ("user")
public interface UserClient {
    @GetMapping("api/users/exists/{username}")
    boolean existsCheck(@PathVariable("username") String username);

    @GetMapping("api/users/{username}")
    UserDto getByUsername(@PathVariable("username") String username);
}
