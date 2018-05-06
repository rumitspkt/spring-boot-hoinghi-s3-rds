package com.dtdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dtdm.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByTitleContaining(String q);
	Post findById(int id);
}
