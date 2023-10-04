package com.mykolyk.clothesstoreapp.repository;

import com.mykolyk.clothesstoreapp.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {
    Optional<Good> findByArticle(String article);

    boolean existsByArticle(String article);
}
