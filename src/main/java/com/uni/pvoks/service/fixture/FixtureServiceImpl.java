package com.uni.pvoks.service.fixture;

import com.uni.pvoks.model.Account;
import com.uni.pvoks.model.Category;
import com.uni.pvoks.model.CategoryType;
import com.uni.pvoks.model.Operation;
import com.uni.pvoks.model.OperationType;
import com.uni.pvoks.model.Product;
import com.uni.pvoks.model.ProductCategory;
import com.uni.pvoks.model.User;
import com.uni.pvoks.repository.AccountRepository;
import com.uni.pvoks.repository.CategoryRepository;
import com.uni.pvoks.repository.OperationRepository;
import com.uni.pvoks.repository.ProductRepository;
import com.uni.pvoks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class FixtureServiceImpl implements FixtureService {

    private static final String USER_EMAIL = "test@user.com";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void initData() {
        Optional<User> existingUser = userRepository.findByEmail(USER_EMAIL);

        if (existingUser.isPresent()) {
            return;
        }

        User user = new User();
        user.setEmail(USER_EMAIL);
        User savedUser = userRepository.save(user);

        Account account = new Account();
        account.setName("Test account");
        account.setUser(savedUser);
        accountRepository.save(account);

        Category firstCategory = new Category();
        firstCategory.setTitle("First category");
        firstCategory.setType(CategoryType.SYSTEM);
        categoryRepository.save(firstCategory);

        Category secondCategory = new Category();
        secondCategory.setTitle("Second category");
        secondCategory.setType(CategoryType.SYSTEM);
        categoryRepository.save(secondCategory);

        Operation outcomeOperation = new Operation();
        outcomeOperation.setCategory(firstCategory);
        outcomeOperation.setAccount(account);
        outcomeOperation.setAmount(100.0);
        outcomeOperation.setType(OperationType.OUTCOME);
        operationRepository.save(outcomeOperation);

        Operation incomeOperation = new Operation();
        incomeOperation.setCategory(firstCategory);
        incomeOperation.setAccount(account);
        incomeOperation.setAmount(120.0);
        incomeOperation.setType(OperationType.INCOME);
        operationRepository.save(incomeOperation);

        Product firstProduct = new Product();
        firstProduct.setName("First product");
        firstProduct.setCost(50.0);
        firstProduct.setOperation(outcomeOperation);
        firstProduct.setCategory(ProductCategory.WATER);
        productRepository.save(firstProduct);

        Product secondProduct = new Product();
        secondProduct.setName("Second product");
        secondProduct.setCost(50.0);
        secondProduct.setOperation(outcomeOperation);
        secondProduct.setCategory(ProductCategory.MEAT);
        productRepository.save(secondProduct);
    }
}
