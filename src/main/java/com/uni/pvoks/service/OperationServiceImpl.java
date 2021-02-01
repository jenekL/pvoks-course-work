package com.uni.pvoks.service;

import com.uni.pvoks.model.Account;
import com.uni.pvoks.model.Category;
import com.uni.pvoks.model.Operation;
import com.uni.pvoks.repository.OperationRepository;
import com.uni.pvoks.rest.dto.OperationInfo;
import com.uni.pvoks.service.api.AccountService;
import com.uni.pvoks.service.api.CategoryService;
import com.uni.pvoks.service.api.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public List<Operation> findAllByPage(PageRequest pageRequest) {
        return operationRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Operation> findAllByAccount(Long accountId) {
        return operationRepository.findAllByAccount_Id(accountId);
    }

    @Override
    public Operation findById(long id) {
        return operationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Operation with id %d does not exist...", id)));
    }

    @Override
    public Operation save(OperationInfo operationInfo) {
        Account existingAccount = accountService.findById(operationInfo.getAccountId());
        Category existingCategory = categoryService.findById(operationInfo.getCategoryId());

        Operation operation = new Operation();
        operation.setAccount(existingAccount);
        operation.setCategory(existingCategory);
        operation.setType(operationInfo.getType());
        operation.setAmount(operationInfo.getAmount());
        return operationRepository.save(operation);
    }

    @Override
    public Operation update(long id, OperationInfo operationInfo) {
        Category existingCategory = categoryService.findById(operationInfo.getCategoryId());

        Operation existingOperation = findById(id);
        existingOperation.setAmount(operationInfo.getAmount());
        existingOperation.setCategory(existingCategory);
        existingOperation.setType(operationInfo.getType());
        return operationRepository.save(existingOperation);
    }

    @Override
    public void delete(long id) {
        Operation existingOperation = findById(id);
        operationRepository.delete(existingOperation);
    }
}
