package org.ewebshop.clients.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("product")
public interface ProductClient {

    @GetMapping("api/products/exists/{id}")
    boolean existsCheck(@PathVariable("id") Integer id);

    @PostMapping("/{id}/decrease/{amount}")
    void decreaseQuantity(@PathVariable("id") int id, @PathVariable("amount") int amount);

    @PostMapping("/{id}/increase/{amount}")
    void increaseQuantity(@PathVariable("id") int id, @PathVariable("amount") int amount);

}
