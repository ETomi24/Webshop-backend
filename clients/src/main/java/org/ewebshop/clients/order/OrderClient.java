package org.ewebshop.clients.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("order")
public interface OrderClient {

    @GetMapping("api/orders/exists/{id}")
    boolean existsCheck(@PathVariable("id") Integer id);

}
