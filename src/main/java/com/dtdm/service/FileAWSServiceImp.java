package com.dtdm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdm.model.FileAWS;
import com.dtdm.repository.FileAWSRepository;

@Service
public class FileAWSServiceImp implements FileAWSService {

	@Autowired
	FileAWSRepository fileAWSRepository;
	
	@Override
	public FileAWS findOne(int id) {
		// TODO Auto-generated method stub
		return fileAWSRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		fileAWSRepository.deleteById(id);
	}

	@Override
	public void save(FileAWS fileAWS) {
		// TODO Auto-generated method stub
		fileAWSRepository.save(fileAWS);
	}
	
}
