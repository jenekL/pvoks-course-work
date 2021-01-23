package com.uni.pvoks.service.api;

import com.uni.pvoks.model.Category;
import com.uni.pvoks.rest.dto.CategoryInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService {
    List<Category> findAllByPage(PageRequest pageRequest);

    Category findById(long id);

    Category save(CategoryInfo categoryInfo);

    Category update(long id, CategoryInfo categoryInfo);

    void delete(long id);
}
