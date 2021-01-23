package com.uni.pvoks.rest;

import com.uni.pvoks.model.Operation;
import com.uni.pvoks.rest.dto.OperationInfo;
import com.uni.pvoks.service.api.OperationService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping("all")
    public ResponseEntity<List<OperationInfo>> getAllOperationsByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortField));
        return ResponseEntity.ok(mapToInfoList(operationService.findAllByPage(pageRequest)));
    }

    @PostMapping
    public ResponseEntity<OperationInfo> createOperation(@RequestBody OperationInfo operationInfo) {
        Operation createdOperation = operationService.save(operationInfo);
        return ResponseEntity.ok(mapToInfo(createdOperation));
    }

    @PutMapping("{id}")
    public ResponseEntity<OperationInfo> updateOperation(@PathVariable Long id,
                                                     @RequestBody OperationInfo OperationInfo) {
        Operation updatedOperation = operationService.update(id, OperationInfo);
        return ResponseEntity.ok(mapToInfo(updatedOperation));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOperation(@PathVariable Long id) {
        operationService.delete(id);
        return ResponseEntity.ok().build();
    }

    private OperationInfo mapToInfo(Operation operation) {
        OperationInfo operationInfo = new OperationInfo();
        operationInfo.setId(operation.getId());
        operationInfo.setType(operation.getType());
        operationInfo.setAmount(operation.getAmount());
        operationInfo.setAccountId(operation.getAccount().getId());
        operationInfo.setCategoryId(operation.getCategory().getId());
        return operationInfo;
    }

    private List<OperationInfo> mapToInfoList(List<Operation> operations) {
        return operations.stream()
                .map(this::mapToInfo)
                .collect(Collectors.toList());
    }
}
