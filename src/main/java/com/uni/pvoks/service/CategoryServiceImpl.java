package com.uni.pvoks.service;

import com.uni.pvoks.model.Category;
import com.uni.pvoks.model.CategoryType;
import com.uni.pvoks.model.User;
import com.uni.pvoks.repository.CategoryRepository;
import com.uni.pvoks.rest.dto.CategoryInfo;
import com.uni.pvoks.service.api.CategoryService;
import com.uni.pvoks.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllByPage(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Category> findAllByUser(Long userId) {
        return categoryRepository.findAllByUser_IdOrType(userId, CategoryType.SYSTEM);
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Category with id %d does not exist...", id)));
    }

    @Override
    public Category save(CategoryInfo categoryInfo) {
        User existingUser = userService.findById(categoryInfo.getUserId());

        Category category = new Category();
        category.setTitle(categoryInfo.getTitle());
        category.setType(categoryInfo.getType());
        category.setUser(existingUser);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(long id, CategoryInfo categoryInfo) {
        Category existingCategory = findById(id);
        existingCategory.setTitle(categoryInfo.getTitle());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void delete(long id) {
        Category existingCategory = findById(id);
        categoryRepository.delete(existingCategory);
    }
}
