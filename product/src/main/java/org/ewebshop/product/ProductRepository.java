package org.ewebshop.product;

import org.ewebshop.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByCategory(Category category);
}