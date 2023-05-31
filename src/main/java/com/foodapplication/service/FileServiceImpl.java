package com.foodapplication.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.foodapplication.exceptions.BadRequestException;

@Component
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) {
		// TODO Auto-generated method stub
		
		String name=file.getOriginalFilename();
		
		String randomId=UUID.randomUUID().toString();
		
		String format=name.substring(name.indexOf('.'), name.length());
		
		if(!format.equals(".png") && !format.equals(".jpeg") && !format.equals(".jpg") )
		{
			System.out.println("\n\n"+format);
			throw new BadRequestException("Only image accpted with format .png, .jpeg, .jpg");
		}
		
		String fileName=randomId+format;
		
		String imagePath= path + File.separator + fileName;
		
		File f= new File(path);
		if(!f.exists())
		{
			  f.mkdir();
	    }
		try {
			Files.copy(file.getInputStream(), Paths.get(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return imagePath;
	}

	@Override
	public InputStream getImage(String path, String fileName) {
		// TODO Auto-generated method stub
		
		String fullPath=path+fileName;
		InputStream inputStream;
		try {
			inputStream= new FileInputStream(fullPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new BadRequestException("No File found");
		}
		return inputStream;
	}

}
