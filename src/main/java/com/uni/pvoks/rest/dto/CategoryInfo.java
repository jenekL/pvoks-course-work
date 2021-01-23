package com.uni.pvoks.rest.dto;

import com.uni.pvoks.model.CategoryType;

public class CategoryInfo {
    private long id;
    private long userId;
    private String title;
    private CategoryType type;

    public CategoryInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
