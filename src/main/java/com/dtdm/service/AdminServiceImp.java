package com.dtdm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdm.model.Admin;
import com.dtdm.repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService{
	
	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public Admin findByUsername(String username) {
		return adminRepository.findByUsername(username);
	}
}
