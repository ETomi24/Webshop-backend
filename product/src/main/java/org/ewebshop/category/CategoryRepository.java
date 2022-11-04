package org.ewebshop.category;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);

    void deleteByName(String name);

    Optional<Category> findByName(String name);

}