package com.leecp.jpa.repository;

import com.leecp.jpa.model.Topic;
import com.leecp.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
