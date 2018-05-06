package com.dtdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdm.model.Post;
import com.dtdm.repository.PostRepository;

@Service
public class PostServiceImp implements PostService {

	@Autowired
	PostRepository postRepository;
	
	@Override
	public Iterable<Post> findAll() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

	@Override
	public List<Post> search(String q) {
		// TODO Auto-generated method stub
		return postRepository.findByTitleContaining(q);
	}

	@Override
	public Post findOne(int id) {
		// TODO Auto-generated method stub
		return postRepository.findById(id);
	}

	@Override
	public void save(Post post) {
		// TODO Auto-generated method stub
		postRepository.save(post);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		postRepository.deleteById(id);
	}
	
}
