package org.ewebshop.category;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ewebshop.category.dto.CategoryCreateRequest;
import org.ewebshop.category.dto.CategoryResponse;
import org.ewebshop.category.dto.CategoryUpdateRequest;
import org.ewebshop.category.exception.DeleteException;
import org.ewebshop.product.exception.IdNotMatchingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable Integer id) {
        try {
            Category category = categoryService.getCategoryById(id);
            return new CategoryResponse(category.getId(), category.getName());
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/all")
    public List<CategoryResponse> getAll() {
        try {
            List<CategoryResponse> categoryList = new ArrayList<>();
            for (Category category : categoryService.getAll()){
                categoryList.add(new CategoryResponse(category.getId(), category.getName()));
            }
            return categoryList;
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        try{
            categoryService.deleteCategory(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        } catch (DeleteException exception) {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
        try {
            log.info(categoryCreateRequest.toString());
            categoryService.createCategory(categoryCreateRequest);
            return new ResponseEntity<>("Category created", HttpStatus.CREATED);
        } catch (EntityExistsException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public void updateCategory(@PathVariable Integer id, @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        try {
            categoryService.updateCategory(id, categoryUpdateRequest);
        } catch (IdNotMatchingException | EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

}
