package com.uni.pvoks.service.api;

import com.uni.pvoks.model.Account;
import com.uni.pvoks.rest.dto.AccountInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AccountService {

    List<Account> findAllByPage(PageRequest pageRequest);

    Account findById(long id);

    Account save(AccountInfo accountInfo);

    Account update(long id, AccountInfo accountInfo);

    void delete(long id);
}
