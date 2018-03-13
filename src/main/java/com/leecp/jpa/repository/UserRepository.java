package com.leecp.jpa.repository;

import com.leecp.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByNickname(String nickName);
}
