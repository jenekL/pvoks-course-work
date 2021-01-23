package com.uni.pvoks.service.api;

import com.uni.pvoks.model.Product;
import com.uni.pvoks.rest.dto.ProductInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {
    List<Product> findAllByPage(PageRequest pageRequest);

    Product findById(long id);

    Product save(ProductInfo productInfo);

    Product update(long id, ProductInfo productInfo);

    void delete(long id);
}
