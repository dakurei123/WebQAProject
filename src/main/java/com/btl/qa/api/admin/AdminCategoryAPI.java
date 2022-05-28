package com.btl.qa.api.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.btl.qa.dto.CategoryDTO;
import com.btl.qa.entity.Category;
import com.btl.qa.repository.CategoryRepository;
import com.btl.qa.util.Convert;

@RestController
public class AdminCategoryAPI {
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("admin/api/category")
	public List<CategoryDTO> getCategory(){
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> dtos = new ArrayList<>();
		for (Category category : categories) {
			dtos.add(Convert.toDTO(category));
		}
		return dtos;
	}
	
	@PostMapping("admin/api/category")
	public boolean addCategory(@RequestBody CategoryDTO dto) {
		Category oldCategory = new Category();
		if (dto.getId() != null) {
			oldCategory = categoryRepository.findOne(dto.getId());
		}
		oldCategory.setName(dto.getName());
		categoryRepository.save(oldCategory);
		return true;
	}
}
