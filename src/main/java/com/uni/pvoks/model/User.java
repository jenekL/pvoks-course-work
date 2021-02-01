package com.uni.pvoks.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "users_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private long id;

    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private final Set<Account> accounts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private final Set<Category> categories = new HashSet<>();

    public User() {
    }

    public long getId() {
        return id;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
