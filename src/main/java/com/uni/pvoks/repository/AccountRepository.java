package com.uni.pvoks.repository;

import com.uni.pvoks.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    List<Account> findAllByUser_Id(long userId);

}
