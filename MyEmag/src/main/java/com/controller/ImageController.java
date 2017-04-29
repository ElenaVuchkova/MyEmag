package com.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.model.Product;
import com.model.dao.ImageDAO;

@Controller
//@SessionAttributes("filename")
@MultipartConfig
public class ImageController {
	

//	//private static final String FILE_LOCATION = "C:\\Users\\hp\\Desktop\\EmagImages\\";
//	private static final String FILE_LOCATION = "C:\\Users\\Elena\\Desktop\\EmagImages\\";
//	
//	
//	@RequestMapping(value="/image/{fileName}", method=RequestMethod.GET)
//	@ResponseBody
//	public void prepareForUpload(@PathVariable("fileName") String fileName, HttpServletResponse resp, Model model) throws IOException {
//		File file = new File(FILE_LOCATION + vzemiToqImage);
//		Files.copy(file.toPath(), resp.getOutputStream());
//	}
	
	//private static final String FILE_PATH = "C:\\Users\\Elena\\Desktop\\EmagImages\\";
	private static final String FILE_PATH = "C:\\Users\\hp\\Desktop\\EmagImages\\";
	//vizualizaciq na kartinkata
	@RequestMapping (value="/image/{productId}/{index}", method=RequestMethod.GET)
	@ResponseBody
	public void getImage(Model viewModel, @PathVariable("productId") Integer productId,
										  @PathVariable("index") Integer index, 
										  HttpServletResponse response){
		ArrayList<String> pics;
		try {
			pics = ImageDAO.getInstance().getAllImagePathsByProduct(productId);
			String p=pics.get(index);
			File file = new File(FILE_PATH + p);
			System.out.println("Koq kartinka zarejda -> "+FILE_PATH+p);
			Files.copy(file.toPath(), response.getOutputStream());
			
//			for(String pic: pics){
//				//imeto na snimkata s product s id=productId
//				File file = new File(FILE_PATH + pic);
//				System.out.println("Koq kartinka zarejda -> "+FILE_PATH+pic);
//				Files.copy(file.toPath(), response.getOutputStream());
//			}
		} catch (SQLException | IOException e) {
			System.out.println("image controller - getImage - "+e.getMessage());
		}		
	}
	
	/*
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String prepareForUpload(HttpSession session) {		
		Product p=(Product)session.getAttribute("product");
		System.out.println(p);
		return "upload";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String receiveUpload(@RequestParam("failche") MultipartFile multiPartFile, Model model, HttpSession session) throws IOException{	
		File fileOnDisk = new File(FILE_LOCATION + multiPartFile.getOriginalFilename());
		Files.copy(multiPartFile.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
		vzemiToqImage = multiPartFile.getOriginalFilename();
		model.addAttribute("filename", multiPartFile.getOriginalFilename());
		return "upload";
	}*/
}