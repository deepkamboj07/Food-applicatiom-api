package com.foodapplication.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public String uploadImage(String path, MultipartFile file);
	public InputStream getImage(String path, String fileName);
}
