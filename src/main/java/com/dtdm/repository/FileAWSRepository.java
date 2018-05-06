package com.dtdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dtdm.model.FileAWS;

@Repository
public interface FileAWSRepository extends JpaRepository<FileAWS, Integer> {
	FileAWS findById(int id);
}
