package com.dtdm.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonUploadService {
	public List<String> uploadFile(MultipartFile multipartFile);
	public void deleteFile(String name);
}
