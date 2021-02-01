package com.uni.pvoks.rest;

import com.uni.pvoks.model.Category;
import com.uni.pvoks.model.Operation;
import com.uni.pvoks.rest.dto.CategoryInfo;
import com.uni.pvoks.rest.dto.OperationInfo;
import com.uni.pvoks.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OperationMapper {

    @Autowired
    private CategoryService categoryService;

    public OperationInfo mapToInfo(Operation operation) {
        OperationInfo operationInfo = new OperationInfo();
        operationInfo.setId(operation.getId());
        operationInfo.setType(operation.getType());
        operationInfo.setAmount(operation.getAmount());
        operationInfo.setAccountId(operation.getAccount().getId());

        Category category = categoryService.findById(operation.getCategory().getId());
        operationInfo.setCategory(category.getTitle());
        return operationInfo;
    }

    public List<OperationInfo> mapToInfoList(List<Operation> operations) {
        return operations.stream()
                .map(this::mapToInfo)
                .collect(Collectors.toList());
    }
}
