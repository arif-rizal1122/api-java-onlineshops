package com.arifRizal.online_shops.repository;

import com.arifRizal.online_shops.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    boolean existByName(String name);
}
