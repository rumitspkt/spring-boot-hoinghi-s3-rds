package com.dtdm.service;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;


@Service
public class AmazonUploadServiceImp implements AmazonUploadService {
	private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

	@PostConstruct
    private void initializeAmazon() {
       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       this.s3client = new AmazonS3Client(credentials);
	}
	
	private void uploadFileTos3bucket(String fileName, File file) {
	    s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
	            .withCannedAcl(CannedAccessControlList.PublicRead));
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
	private String generateFileName(MultipartFile multiPart) {
	    return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}
	
	@Override
	public List<String> uploadFile(MultipartFile multipartFile) {

		List<String> listString = new ArrayList<String>();
	    String fileUrl = "";
	    try {
	        File file = convertMultiPartToFile(multipartFile);
	        String fileName = generateFileName(multipartFile);
	        fileUrl = "https://"+ bucketName + ".s3.amazonaws.com/" + fileName;
	        uploadFileTos3bucket(fileName, file);
	        file.delete();
	        listString.add(fileUrl);
	        listString.add(fileName);
	    } catch (Exception e) {
	       e.printStackTrace();
	       return null;
	    }
	    
	    return listString;
	}
	
	@Override
	public void deleteFile(String name) {
        s3client.deleteObject(new DeleteObjectRequest(bucketName, name));
    }
	
	
	
	
	
}
