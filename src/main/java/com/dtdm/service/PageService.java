package com.dtdm.service;

import com.dtdm.model.Page;


public interface PageService {
	Page findOne(String header);
	void save(Page page);
}
