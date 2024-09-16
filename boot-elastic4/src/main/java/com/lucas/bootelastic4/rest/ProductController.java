package com.lucas.bootelastic4.rest;

import com.lucas.bootelastic4.modules.product.domain.Product;
import com.lucas.bootelastic4.modules.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;


    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        if(product == null) {throw new IllegalArgumentException("Product is null");}

        Product result = productService.saveProduct(product);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> all = productService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
