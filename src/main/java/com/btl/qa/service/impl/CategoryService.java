package com.btl.qa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btl.qa.dto.CategoryDTO;
import com.btl.qa.entity.Category;
import com.btl.qa.repository.CategoryRepository;
import com.btl.qa.service.ICategoryService;
import com.btl.qa.util.Convert;

@Service
public class CategoryService implements ICategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryDTO> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
		for (Category category : categories) {
			dtos.add(Convert.toDTO(category));
		}
		return dtos;
	}
}
