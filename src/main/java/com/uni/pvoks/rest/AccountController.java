package com.uni.pvoks.rest;

import com.uni.pvoks.model.Account;
import com.uni.pvoks.rest.dto.AccountInfo;
import com.uni.pvoks.service.api.AccountService;
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
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("all")
    public ResponseEntity<List<AccountInfo>> getAllAccountsByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortField));
        return ResponseEntity.ok(mapToInfoList(accountService.findAllByPage(pageRequest)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AccountInfo>> getAllByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(mapToInfoList(accountService.findAllByUser(userId)));
    }

    @PostMapping
    public ResponseEntity<AccountInfo> createAccount(@RequestBody AccountInfo accountInfo) {
        Account createdAccount = accountService.save(accountInfo);
        return ResponseEntity.ok(mapToInfo(createdAccount));
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountInfo> updateAccount(@PathVariable Long id,
                                                     @RequestBody AccountInfo AccountInfo) {
        Account updatedAccount = accountService.update(id, AccountInfo);
        return ResponseEntity.ok(mapToInfo(updatedAccount));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.ok().build();
    }

    private AccountInfo mapToInfo(Account account) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setId(account.getId());
        accountInfo.setName(account.getName());
        accountInfo.setUserId(account.getUser().getId());
        return accountInfo;
    }

    private List<AccountInfo> mapToInfoList(List<Account> accounts) {
        return accounts.stream()
                .map(this::mapToInfo)
                .collect(Collectors.toList());
    }
}
