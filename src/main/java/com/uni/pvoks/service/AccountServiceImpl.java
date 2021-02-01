package com.uni.pvoks.service;

import com.uni.pvoks.model.Account;
import com.uni.pvoks.model.User;
import com.uni.pvoks.repository.AccountRepository;
import com.uni.pvoks.rest.dto.AccountInfo;
import com.uni.pvoks.service.api.AccountService;
import com.uni.pvoks.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAllByPage(PageRequest pageRequest) {
        return accountRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Account> findAllByUser(Long userId) {
        return accountRepository.findAllByUser_Id(userId);
    }

    @Override
    public Account findById(long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Account with id %d does not exist...", id)));
    }

    @Override
    public Account save(AccountInfo accountInfo) {
        User existingUser = userService.findById(accountInfo.getUserId());

        Account account = new Account();
        account.setUser(existingUser);
        account.setName(accountInfo.getName());
        return accountRepository.save(account);
    }

    @Override
    public Account update(long id, AccountInfo accountInfo) {
        Account existingAccount = findById(id);
        existingAccount.setName(accountInfo.getName());
        return accountRepository.save(existingAccount);
    }

    @Override
    public void delete(long id) {
        Account existingAccount = findById(id);
        accountRepository.delete(existingAccount);
    }
}
