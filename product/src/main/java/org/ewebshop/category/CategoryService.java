package org.ewebshop.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(String name) throws EntityExistsException{
        if(categoryRepository.existsById(name)) {
            Category category = new Category(name);
            categoryRepository.save(category);
        } else {
            throw new EntityExistsException("This Category is already exists");
        }
    }

    public void deleteCategory(String name) throws EntityNotFoundException{
        if(categoryRepository.existsById(name)){
            categoryRepository.deleteById(name);
        } else {
            throw new EntityNotFoundException("This Category is not existing");
        }
    }

    public Category getCategory(String name) throws EntityNotFoundException {
        Optional<Category> category = categoryRepository.findById(name);
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
