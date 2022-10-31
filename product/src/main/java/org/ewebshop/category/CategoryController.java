package org.ewebshop.category;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{name}")
    public ResponseEntity<?> getCategory(@PathVariable String name) {
        try {
            return new ResponseEntity<>(categoryService.getCategory(name), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteCategory(@PathVariable String name) {
        try{
            categoryService.deleteCategory(name);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
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

}
