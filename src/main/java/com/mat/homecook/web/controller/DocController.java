package com.mat.homecook.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.mat.homecook.model.Doc;
import com.mat.homecook.service.DocStorageService;

@Controller
public class DocController {

	@Autowired 
	private DocStorageService docStorageService;
	
	@GetMapping("/upload")
	public String get(Model model) {
		List<Doc> docs = docStorageService.getFiles();
		model.addAttribute("docs", docs);
		return "doc";
	}
	
	@PostMapping("/uploadFiles")
	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		for (MultipartFile file: files) {
			docStorageService.saveFile(file);
			
		}
		return "redirect:upload";
	}
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		Doc doc = docStorageService.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}

	@GetMapping("/search")
	public String getEmployees(Model model,String keyword)
	{

			if(keyword != null){
				model.addAttribute("employees",docStorageService.findByKeyword(keyword));
			}
			else {
				model.addAttribute("employees", docStorageService.getFiles());
			}
			return "doc2";
	}

}
