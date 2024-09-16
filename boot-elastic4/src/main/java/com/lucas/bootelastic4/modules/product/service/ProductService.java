package com.lucas.bootelastic4.modules.product.service;

import com.lucas.bootelastic4.modules.product.domain.Product;
import com.lucas.bootelastic4.modules.repository.jpa.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductJpaRepository productRepository;

    @Transactional
    public Product saveProduct(Product product) {
        Product save = productRepository.save(product);
        log.info("save = {}", save);
        return save;
    }


    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
