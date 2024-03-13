package com.example.shop.api.repository;

import com.example.shop.api.domain.Product;
import com.example.shop.api.repository.querydsl.ProductRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long>, ProductRepositoryCustom {

    Page<Product> findAll(Pageable pageable);

    Page<Product> getProductPage(Pageable pageable);
}
