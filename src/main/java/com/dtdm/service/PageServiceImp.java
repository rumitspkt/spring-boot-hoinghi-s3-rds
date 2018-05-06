package com.dtdm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdm.model.Page;
import com.dtdm.repository.PageRepository;

@Service
public class PageServiceImp implements PageService{
	
	@Autowired
	PageRepository pageRepository;
	
	@Override
	public Page findOne(String header) {
		return pageRepository.findByHeader(header);
	}
	
	@Override
	public void save(Page page) {
		pageRepository.save(page);
	}
}
