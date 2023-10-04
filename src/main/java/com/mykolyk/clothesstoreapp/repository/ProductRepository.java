package com.mykolyk.clothesstoreapp.repository;

import com.mykolyk.clothesstoreapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByArticle(String article);

    boolean existsByArticle(String article);
}
