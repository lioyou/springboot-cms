package com.leecp.jpa.repository;

import com.leecp.jpa.model.Comment;
import com.leecp.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
