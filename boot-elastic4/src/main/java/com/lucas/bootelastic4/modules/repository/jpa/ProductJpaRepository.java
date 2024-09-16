package com.lucas.bootelastic4.modules.repository.jpa;

import com.lucas.bootelastic4.modules.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}
