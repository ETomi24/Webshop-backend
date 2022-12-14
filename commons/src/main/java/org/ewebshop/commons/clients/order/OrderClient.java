package org.ewebshop.commons.clients.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("order")
public interface OrderClient {

    @GetMapping("api/orders/exists/{id}")
    boolean existsCheck(@PathVariable("id") Integer id, @RequestHeader("Authorization") String bearerToken);

}
