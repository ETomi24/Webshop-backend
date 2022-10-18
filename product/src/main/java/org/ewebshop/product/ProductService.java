package org.ewebshop.product;

import lombok.AllArgsConstructor;
import org.ewebshop.category.CategoryService;
import org.ewebshop.product.exception.IdNotMatchingException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public void createProduct(ProductCreateRequest productCreateRequest) throws EntityExistsException {
        categoryService.getCategory(productCreateRequest.categoryId());
        Product product = Product.builder()
                .description(productCreateRequest.description())
                .price(productCreateRequest.price())
                .quantity(productCreateRequest.quantity())
                .name(productCreateRequest.name())
                .categoryId(productCreateRequest.categoryId())
                .pictureUrl(productCreateRequest.pictureUrl())
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

    public void updateProduct(int id, Product product) throws IdNotMatchingException,EntityNotFoundException {
        if (id != product.getId()){throw new IdNotMatchingException();}
        //Megkeresem hogy létezik-e az adott termék,ha nem, dob hibát
        this.getProduct(id);
        productRepository.save(product);
    }
}
