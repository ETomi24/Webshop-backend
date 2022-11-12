package org.ewebshop.commons.clients.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("product")
public interface ProductClient {

    @GetMapping("api/products/exists/{id}")
    boolean existsCheck(@PathVariable("id") Integer id);

    @PostMapping(value = "api/products/decrease/{id}", consumes = "application/json")
    void decreaseQuantity(@PathVariable("id") int id, @RequestBody int amount);

    @PostMapping(value = "api/products/increase/{id}", consumes = "application/json")
    void increaseQuantity(@PathVariable("id") int id, @RequestBody int amount);

}
