package com.rivers.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.rivers.demo.entity.User;
import com.rivers.demo.repository.UserRepository;

public interface UserRepository extends MongoRepository<User, Long>{
    User findById(String id);
    User findByOffset(Long offset);

    @Query("{'offset' : { $gte: ?0, $lte: ?1 }}")
    List<User> findInOffsetRange(Long lowerOffset, Long upperOffset);
}