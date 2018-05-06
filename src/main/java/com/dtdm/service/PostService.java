package com.dtdm.service;

import java.util.List;

import com.dtdm.model.Post;

public interface PostService {
	Iterable<Post> findAll();

    List<Post> search(String q);

    Post findOne(int id);

    void save(Post post);

    void delete(int id);
}
