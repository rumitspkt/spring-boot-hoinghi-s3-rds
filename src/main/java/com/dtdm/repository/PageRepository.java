package com.dtdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dtdm.model.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, String>{
	Page findByHeader(String header);
}
