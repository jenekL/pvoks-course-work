package com.uni.pvoks.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(generator = "operations_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "operations_id_seq", sequenceName = "operations_id_seq", allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private double amount;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private final Set<Product> products = new HashSet<>();

    public Operation() {
    }

    public long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
