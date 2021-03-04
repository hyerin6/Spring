package com.example.awss3.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.awss3.domain.Image;
import com.example.awss3.s3.S3Uploader;
import com.example.awss3.service.ImageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AwsS3TestController {

	private static final String FAIL = "image upload fail";

	private final S3Uploader s3Uploader;
	private final ImageService imageService;

	@PostMapping("/upload")
	public String upload(@RequestParam("data") MultipartFile multipartFile) {
		String path = "";

		try {
			path = s3Uploader.upload(multipartFile, "testImage");

			Image image = Image.builder()
				.fileName(multipartFile.getOriginalFilename())
				.filePath(path)
				.build();

			imageService.addImage(image);
		} catch (Exception e) {
			return FAIL;
		}

		return path;
	}

}
