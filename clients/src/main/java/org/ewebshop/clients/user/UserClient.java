package org.ewebshop.clients.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient ("user")
public interface UserClient {
    @GetMapping(path = "api/users/unique/{username}")
    boolean uniqueCheck(@PathVariable("username") String username);
}
