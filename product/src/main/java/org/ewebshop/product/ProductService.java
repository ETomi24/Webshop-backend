package org.ewebshop.product;

import lombok.AllArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.ewebshop.category.Category;
import org.ewebshop.category.CategoryService;
import org.ewebshop.product.dto.ProductCreateRequest;
import org.ewebshop.product.dto.ProductUpdateRequest;
import org.ewebshop.product.exception.IdNotMatchingException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public void createProduct(ProductCreateRequest productCreateRequest) throws EntityNotFoundException {
        categoryService.getCategoryByName(productCreateRequest.category());

        Product product = Product.builder()
                .description(productCreateRequest.description())
                .price(productCreateRequest.price())
                .quantity(productCreateRequest.quantity())
                .name(productCreateRequest.name())
                .category(categoryService.getCategoryByName(productCreateRequest.category()))
                .picture(Base64.decodeBase64(productCreateRequest.picture().split(",")[1]))
                .build();
        productRepository.save(product);
    }


    public void deleteProduct(int id) throws EntityNotFoundException{
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("This product is not existing");
        }
    }


    public Product getProduct(int id) throws EntityNotFoundException{
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        } else {
            throw new EntityNotFoundException("This product is not existing");
        }
    }


    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    public void increaseQuantity(int id, int amount) throws EntityNotFoundException{
        Product product = getProduct(id);
        product.setQuantity(product.getQuantity() + amount);
        productRepository.save(product);
    }


    public void decreaseQuantity(int id, int amount) {
        Product product = getProduct(id);
        product.setQuantity(product.getQuantity() - amount);
        productRepository.save(product);
    }


    public void updateProduct(int id, ProductUpdateRequest productUpdateRequest) throws IdNotMatchingException,EntityNotFoundException {
        if (id != productUpdateRequest.id()){throw new IdNotMatchingException();}
        this.getProduct(id);
        //Megkeresem hogy létezik-e az adott termék,ha nem, dob hibát
        Product product = Product.builder()
                .id(productUpdateRequest.id())
                .description(productUpdateRequest.description())
                .price(productUpdateRequest.price())
                .quantity(productUpdateRequest.quantity())
                .name(productUpdateRequest.name())
                .category(categoryService.getCategoryByName(productUpdateRequest.category()))
                .picture(Base64.decodeBase64(productUpdateRequest.picture().split(",")[1]))
                .build();
        productRepository.save(product);
    }


    public boolean productExists(int id){
        return productRepository.existsById(id);
    }

}
