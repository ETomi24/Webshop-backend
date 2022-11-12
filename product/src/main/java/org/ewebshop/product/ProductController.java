package org.ewebshop.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ewebshop.category.dto.CategoryResponse;
import org.ewebshop.product.dto.ProductCreateRequest;
import org.ewebshop.product.dto.ProductResponse;
import org.ewebshop.product.dto.ProductUpdateRequest;
import org.ewebshop.product.exception.IdNotMatchingException;
import org.ewebshop.product.exception.MinusQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Integer id){
        try {
            Product product = productService.getProduct(id);
            return new ProductResponse(
                    product.getId(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getName(),
                    new CategoryResponse(product.getCategory().getId(), product.getCategory().getName()),
                    product.getPicture()
            );
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/all")
    public List<ProductResponse> getAll() {
        try {
            List<ProductResponse> productResponseList = new ArrayList<>();
            for (Product product : productService.getAll()){
                productResponseList.add(new ProductResponse(
                        product.getId(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getName(),
                        new CategoryResponse(product.getCategory().getId(), product.getCategory().getName()),
                        product.getPicture())
                );
            }
            return productResponseList;
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        try {
            productService.createProduct(productCreateRequest);
            return new ResponseEntity<>("Product created", HttpStatus.CREATED);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody ProductUpdateRequest productUpdateRequest) {
        try {
            productService.updateProduct(id,productUpdateRequest);
        } catch (IdNotMatchingException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PutMapping("/increase/{id}")
    public void increaseQuantity(@PathVariable int id, @RequestBody int amount) {
        try {
            productService.increaseQuantity(id,amount);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PutMapping("/decrease/{id}")
    public void decreaseQuantity(@PathVariable int id, @RequestBody int amount) {
        try {
            productService.decreaseQuantity(id,amount);
        } catch (EntityNotFoundException | MinusQuantityException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/exists/{id}")
    public boolean existsCheck(@PathVariable Integer id) {
        try {
            return productService.productExists(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

}
