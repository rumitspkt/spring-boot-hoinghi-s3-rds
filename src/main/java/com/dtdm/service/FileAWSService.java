package com.dtdm.service;

import com.dtdm.model.FileAWS;

public interface FileAWSService {
	FileAWS findOne(int id);
	void delete(int id);
	void save(FileAWS fileAWS);
}
