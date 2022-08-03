package com.onlinebookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebookstore.dao.CategoryRepository;
import com.onlinebookstore.dto.Category;
import com.onlinebookstore.exception.CategoryException;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category createCategory(Category category) throws CategoryException {
		if(category == null) {
			throw new CategoryException("Category cannot be Null!");
		}
		Category categoryAdded;
		try {
			categoryAdded = this.categoryRepository.save(category);
		} catch (Exception e) {
			throw new CategoryException("Category could not be Added!");
		}
		return categoryAdded;
	}

	@Override
	public Category getCategoryByCategoryId(Integer categoryId) throws CategoryException {
		// TODO Auto-generated method stub
		Optional<Category> foundCategory = this.categoryRepository.findById(categoryId);
		if (foundCategory.isEmpty()) {
			throw new CategoryException("Category doesn't exists for categoryid " + categoryId);
		}else if(categoryId==null) {
			throw new CategoryException("Enter valid Category Id!");
		}
		return foundCategory.get();
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException {
		// TODO Auto-generated method stub
		if(category == null) {
			throw new CategoryException("Category cannot be Null!");
		}
		Optional<Category> foundCategory = this.categoryRepository.findById(category.getCategoryId());

		if (foundCategory.isEmpty()) {
			throw new CategoryException("Category doesnot exists for id " + category.getCategoryId());
		}
		return this.categoryRepository.save(category);
	}

	@Override
	public String deleteCategoryById(Integer categoryId) throws CategoryException {
		// TODO Auto-generated method stub
		String isDeleted;
		Optional<Category> foundCategory = categoryRepository.findById(categoryId);
		if (foundCategory.isEmpty()) {
			throw new CategoryException("Category does not exist for id " + categoryId);
		}else if(categoryId == null) {
			throw new CategoryException("Enter valid Category Id!");
		}else {
			categoryRepository.delete(foundCategory.get());
			isDeleted = "Successful";
		}
		return isDeleted;

	}

	@Override
	public List<Category> getAllCategory() throws CategoryException {
		// TODO Auto-generated method stub
		List<Category> categoryList = this.categoryRepository.findAll();
		if(categoryList.isEmpty()) {
			throw new CategoryException("No Category Found");
		}
		return this.categoryRepository.findAll();
	}
}
