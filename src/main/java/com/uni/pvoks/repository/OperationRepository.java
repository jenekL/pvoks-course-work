package com.uni.pvoks.repository;

import com.uni.pvoks.model.Operation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends PagingAndSortingRepository<Operation, Long> {
}
