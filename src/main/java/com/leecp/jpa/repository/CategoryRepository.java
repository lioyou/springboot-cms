package com.leecp.jpa.repository;

import com.leecp.jpa.model.Category;
import com.leecp.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
