package com.example.shop.api.service.impl;

import com.example.shop.api.domain.Category;
import com.example.shop.api.domain.Product;
import com.example.shop.api.dto.ProductRequestDto;
import com.example.shop.api.repository.CategoryRepository;
import com.example.shop.api.repository.ProductRepository;
import com.example.shop.global.config.QueryDslConfig;
import com.example.shop.global.exception.CategoryNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StopWatch;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE,
        connection = EmbeddedDatabaseConnection.H2)
@Import({QueryDslConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceImplTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @BeforeAll
    void createData(){
        int value = 100000000;
        categoryRepository.save(new Category("All",null));
        for(long i = 1 ; i < 5 ; i++){
            categoryRepository.save(new Category(Long.toString(i), categoryRepository.findById(i).orElseThrow(CategoryNotFoundException::new).getCategoryPathName()));
        }
        for (int i = 0 ; i < 400000; i++){
            productRepository.save(new Product(
                    new ProductRequestDto(Integer.toString(value - i),value - i,100,"NONE",4L), categoryRepository.findById(4L).orElseThrow(CategoryNotFoundException::new)));
        }
    }
    @Order(1)
    @Test
    void getProductPage() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        productRepository.getProductPage(PageRequest.of(0, 30,
                Sort.by(Sort.Direction.ASC, "price")));
        productRepository.getProductPage(PageRequest.of(2, 30,
                Sort.by(Sort.Direction.ASC, "price")));
        productRepository.getProductPage(PageRequest.of(7, 30,
                Sort.by(Sort.Direction.ASC, "price")));
        productRepository.getProductPage(PageRequest.of(10, 30,
                Sort.by(Sort.Direction.ASC, "price")));

        productRepository.getProductPage(PageRequest.of(0, 30,
                Sort.by(Sort.Direction.DESC, "price")));
        productRepository.getProductPage(PageRequest.of(0, 30,
                Sort.by(Sort.Direction.ASC, "productName")));
        productRepository.getProductPage(PageRequest.of(0, 30,
                Sort.by(Sort.Direction.DESC, "productName")));
        stopWatch.stop();
        System.out.println("###################################");
        System.out.println(stopWatch.prettyPrint());
        System.out.println("###################################");

    }
    @Order(2)
    @Test
    void getProducts() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        productRepository.findAll(PageRequest.of(0, 30,
                Sort.by(Sort.Direction.ASC, "price")));

        productRepository.getProductPage(PageRequest.of(2, 30,
                Sort.by(Sort.Direction.ASC, "price")));
        productRepository.getProductPage(PageRequest.of(7, 30,
                Sort.by(Sort.Direction.ASC, "price")));
        productRepository.getProductPage(PageRequest.of(10, 30,
                Sort.by(Sort.Direction.ASC, "price")));

        productRepository.getProductPage(PageRequest.of(0, 30,
                Sort.by(Sort.Direction.DESC, "price")));
        productRepository.getProductPage(PageRequest.of(0, 30,
                Sort.by(Sort.Direction.ASC, "productName")));
        productRepository.getProductPage(PageRequest.of(0, 30,
                Sort.by(Sort.Direction.DESC, "productName")));

        stopWatch.stop();
        System.out.println("###################################");
        System.out.println(stopWatch.prettyPrint());
        System.out.println("###################################");
    }
}