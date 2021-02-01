package com.uni.pvoks.repository;

import com.uni.pvoks.model.Operation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends PagingAndSortingRepository<Operation, Long> {
    List<Operation> findAllByAccount_Id(long accountId);
}
