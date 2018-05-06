package com.dtdm.service;

import com.dtdm.model.Admin;

public interface AdminService {
	Admin findByUsername(String username);
}
