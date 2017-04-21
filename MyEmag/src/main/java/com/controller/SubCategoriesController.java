package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.dao.SubcategoryDAO;

@RestController
public class SubCategoriesController {

	@ResponseBody
	@RequestMapping(value = "/subcategories/get/{cat}", method = RequestMethod.GET)
	public ArrayList<String> getSubCategories(@PathVariable (value = "cat") String category){
		try {
			return SubcategoryDAO.getInstance().getAllSubcategoryByCategory(category);
		} catch (SQLException e) {
			return new ArrayList<>();
		}
	}
}
