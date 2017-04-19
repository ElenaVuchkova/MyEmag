package com.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.model.Product;
import com.model.dao.ProductDAO;

@Controller
@SessionAttributes("filename")
@MultipartConfig
public class ProductController {
	
	private String imageName;
	static String sep=File.separator;
	private static final String FILE_LOCATION = sep+"Users"+sep+"hp"+sep+"images";

	@RequestMapping(value="/product", method=RequestMethod.GET)
	public String createProduct (Model m) {
		m.addAttribute("product", new Product());
		return "product";
	}
	
	@RequestMapping(value="/product", method=RequestMethod.POST)
	public String receiveUpload(@RequestParam("failche") MultipartFile multiPartFile, @ModelAttribute Product p,  Model model) throws IOException{
		File fileOnDisk = new File(FILE_LOCATION + multiPartFile.getOriginalFilename());
		Files.copy(multiPartFile.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
		imageName = multiPartFile.getOriginalFilename();
		ArrayList<String> imagePaths=new ArrayList<>();
		imagePaths.add(imageName);
		p.setImagePaths(imagePaths);
		try {
			ProductDAO.getInstance().addProduct(p);
		} catch (SQLException e) {
			System.out.println("SQL - " + e.getMessage());
		}
		model.addAttribute("filename", multiPartFile.getOriginalFilename());
		return "index";
	}
	
//	@RequestMapping(value="/image/{fileName}", method=RequestMethod.GET)
//	@ResponseBody
//	public void prepareForUpload(@PathVariable("fileName") String fileName, HttpServletResponse resp, Model model) throws IOException {
//		File file = new File(FILE_LOCATION + imageName);
//		Files.copy(file.toPath(), resp.getOutputStream());
//	}
}
