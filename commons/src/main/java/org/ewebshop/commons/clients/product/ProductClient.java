package org.ewebshop.commons.clients.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("product")
public interface ProductClient {

    @GetMapping("api/products/exists/{id}")
    boolean existsCheck(@PathVariable("id") Integer id);

    @PutMapping(value = "api/products/decrease/{id}", consumes = "application/json")
    void decreaseQuantity(@PathVariable("id") int id, @RequestBody int amount, @RequestHeader("Authorization") String bearerToken);

    @PutMapping(value = "api/products/increase/{id}", consumes = "application/json")
    void increaseQuantity(@PathVariable("id") int id, @RequestBody int amount, @RequestHeader("Authorization") String bearerToken);

}
