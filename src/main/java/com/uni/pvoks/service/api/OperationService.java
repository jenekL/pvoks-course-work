package com.uni.pvoks.service.api;

import com.uni.pvoks.model.Operation;
import com.uni.pvoks.rest.dto.OperationInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OperationService {
    List<Operation> findAllByPage(PageRequest pageRequest);

    Operation findById(long id);

    Operation save(OperationInfo operationInfo);

    Operation update(long id, OperationInfo operationInfo);

    void delete(long id);
}
