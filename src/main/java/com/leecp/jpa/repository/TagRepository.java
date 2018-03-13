package com.leecp.jpa.repository;

import com.leecp.jpa.model.Tag;
import com.leecp.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepository extends JpaRepository<Tag, Integer> {

}
