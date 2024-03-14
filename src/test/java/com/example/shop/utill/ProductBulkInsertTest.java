package com.example.shop.utill;

import com.example.shop.api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

@SpringBootTest
public class ProductBulkInsertTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void bulkInsert(){
        LongStream.range(0,10000*10)
                .mapToObj( i -> ProductFixtureFactory.create(i,3L));
    }
}
