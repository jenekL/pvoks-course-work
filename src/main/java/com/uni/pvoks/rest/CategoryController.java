package com.uni.pvoks.rest;

import com.uni.pvoks.model.Category;
import com.uni.pvoks.rest.dto.CategoryInfo;
import com.uni.pvoks.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("all")
    public ResponseEntity<List<CategoryInfo>> getAllCategoriesByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortField));
        return ResponseEntity.ok(mapToInfoList(categoryService.findAllByPage(pageRequest)));
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<CategoryInfo>> getCategoriesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(mapToInfoList(categoryService.findAllByUser(userId)));
    }

    @PostMapping
    public ResponseEntity<CategoryInfo> createCategory(@RequestBody CategoryInfo categoryInfo) {
        Category createdCategory = categoryService.save(categoryInfo);
        return ResponseEntity.ok(mapToInfo(createdCategory));
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryInfo> updateCategory(@PathVariable Long id,
                                                       @RequestBody CategoryInfo CategoryInfo) {
        Category updatedCategory = categoryService.update(id, CategoryInfo);
        return ResponseEntity.ok(mapToInfo(updatedCategory));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    private CategoryInfo mapToInfo(Category category) {
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setId(category.getId());
        categoryInfo.setTitle(category.getTitle());
        Optional.ofNullable(category.getUser()).ifPresent(user->categoryInfo.setUserId(user.getId()));
        categoryInfo.setType(category.getType());
        return categoryInfo;
    }

    private List<CategoryInfo> mapToInfoList(List<Category> categories) {
        return categories.stream()
                .map(this::mapToInfo)
                .collect(Collectors.toList());
    }
}
