package com.uni.pvoks.rest;

import com.uni.pvoks.model.Product;
import com.uni.pvoks.rest.dto.ProductInfo;
import com.uni.pvoks.service.api.ProductService;
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
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("all")
    public ResponseEntity<List<ProductInfo>> getAllProductsByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortField));
        return ResponseEntity.ok(mapToInfoList(productService.findAllByPage(pageRequest)));
    }

    @GetMapping("operation/{operationId}")
    public ResponseEntity<List<ProductInfo>> getProductsByOperation(@PathVariable Long operationId) {
        return ResponseEntity.ok(mapToInfoList(productService.findAllByOperation(operationId)));
    }

    @PostMapping
    public ResponseEntity<ProductInfo> createProduct(@RequestBody ProductInfo productInfo) {
        Product createdProduct = productService.save(productInfo);
        return ResponseEntity.ok(mapToInfo(createdProduct));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductInfo> updateProduct(@PathVariable Long id,
                                                     @RequestBody ProductInfo ProductInfo) {
        Product updatedProduct = productService.update(id, ProductInfo);
        return ResponseEntity.ok(mapToInfo(updatedProduct));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    private ProductInfo mapToInfo(Product product) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(product.getId());
        productInfo.setName(product.getName());
        productInfo.setCategory(product.getCategory());
        productInfo.setCost(product.getCost());
        productInfo.setOperationId(product.getOperation().getId());
        return productInfo;
    }

    private List<ProductInfo> mapToInfoList(List<Product> products) {
        return products.stream()
                .map(this::mapToInfo)
                .collect(Collectors.toList());
    }
}
