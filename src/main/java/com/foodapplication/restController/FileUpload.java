package com.foodapplication.restController;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.foodapplication.exceptions.BadRequestException;
import com.foodapplication.service.FileService;

@RestController
@RequestMapping("/api/file")
@CrossOrigin("*")
public class FileUpload {

	@Value("${project.imageFolder}")
	private String path;
	
	@Autowired
	private FileService fileService;
	
	@PostMapping("/upload")
	public String uploadImage(@RequestParam("image") MultipartFile file)
	{
		return fileService.uploadImage(path, file);
	}
	
	@GetMapping("/image/{name}")
	public void getImage(@PathVariable String name,
							HttpServletResponse response)
	{
		InputStream res= this.fileService.getImage(path, name);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		try {
			StreamUtils.copy(res, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new BadRequestException("Image Bot Found");
		}
	}
}
