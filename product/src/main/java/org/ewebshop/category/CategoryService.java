package org.ewebshop.category;

import lombok.AllArgsConstructor;
import org.ewebshop.category.dto.CategoryCreateRequest;
import org.ewebshop.category.dto.CategoryUpdateRequest;
import org.ewebshop.category.exception.DeleteException;
import org.ewebshop.product.Product;
import org.ewebshop.product.ProductRepository;
import org.ewebshop.product.exception.IdNotMatchingException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryCreateRequest categoryCreateRequest) throws EntityExistsException{
        if(!categoryRepository.existsByName(categoryCreateRequest.name())) {
            Category category = new Category();
            category.setName(categoryCreateRequest.name());
            categoryRepository.save(category);
        } else {
            throw new EntityExistsException("This Category is already exists");
        }
    }

    public void updateCategory(int id, CategoryUpdateRequest categoryUpdateRequest) throws IdNotMatchingException, EntityNotFoundException {
        if (id != categoryUpdateRequest.id()){throw new IdNotMatchingException();}
        Category category = getCategoryById(id);
        category.setName(categoryUpdateRequest.name());
        List<Product> products = getAllProductByCategory(category);
        for (Product product : products) {
            product.setCategory(category);
        }
        categoryRepository.save(category);
        productRepository.saveAll(products);
    }

    public List<Product> getAllProductByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    public int productCountByCategory(Category category) {
        return this.getAllProductByCategory(category).size();
    }

    public boolean deletable(Category category) {
        return productCountByCategory(category) == 0;
    }

    public void deleteCategory(Integer id) throws EntityNotFoundException, DeleteException {
        Category category = getCategoryById(id);
        if(deletable(category)){
            categoryRepository.deleteById(id);
        } else {
            throw new DeleteException();
        }
    }

    public Category getCategoryById(int id) throws EntityNotFoundException {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        } else {
            throw new EntityNotFoundException("This Category is not existing");
        }
    }

    public Category getCategoryByName(String name) throws EntityNotFoundException {
        Optional<Category> category = categoryRepository.findByName(name);
        if(category.isPresent()){
            return category.get();
        } else {
            throw new EntityNotFoundException("This Category is not existing");
        }
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
