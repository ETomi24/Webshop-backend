package org.ewebshop.product;

import lombok.AllArgsConstructor;
import org.ewebshop.category.Category;
import org.ewebshop.category.CategoryService;
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
        try {
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
        } catch (EntityNotFoundException exception) {
            throw new EntityNotFoundException("A megadott kategória nem létezik");
        }
    }

    public void removeProduct(int id) throws EntityNotFoundException{
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Ilyen termék nem létezik");
        }
    }

    public Product getProduct(int id) throws EntityNotFoundException{
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        } else {
            throw new EntityNotFoundException("Ilyen termék nem létezik");
        }
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }


}
