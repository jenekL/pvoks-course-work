package com.uni.pvoks.repository;

import com.uni.pvoks.model.Category;
import com.uni.pvoks.model.CategoryType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    List<Category> findAllByUser_IdOrType(long user_id, CategoryType type);
}
