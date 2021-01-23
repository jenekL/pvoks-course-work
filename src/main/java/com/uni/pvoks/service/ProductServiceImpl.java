package com.uni.pvoks.service;

import com.uni.pvoks.model.Operation;
import com.uni.pvoks.model.Product;
import com.uni.pvoks.repository.ProductRepository;
import com.uni.pvoks.rest.dto.ProductInfo;
import com.uni.pvoks.service.api.OperationService;
import com.uni.pvoks.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private OperationService operationService;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllByPage(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).getContent();
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Product with id %d does not exist...", id)));
    }

    @Override
    public Product save(ProductInfo productInfo) {
        Operation existingOperation = operationService.findById(productInfo.getOperationId());

        Product product = new Product();
        product.setOperation(existingOperation);
        product.setName(productInfo.getName());
        product.setCategory(productInfo.getCategory());
        product.setCost(productInfo.getCost());
        return productRepository.save(product);
    }

    @Override
    public Product update(long id, ProductInfo productInfo) {
        Product existingProduct = findById(id);
        existingProduct.setName(productInfo.getName());
        existingProduct.setCategory(productInfo.getCategory());
        existingProduct.setCost(productInfo.getCost());
        return productRepository.save(existingProduct);
    }

    @Override
    public void delete(long id) {
        Product existingProduct = findById(id);
        productRepository.delete(existingProduct);
    }
}
