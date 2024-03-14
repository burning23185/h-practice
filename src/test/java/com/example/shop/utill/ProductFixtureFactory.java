package com.example.shop.utill;

import com.example.shop.api.domain.Product;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

class ProductFixtureFactory {

    static public Product create(Long seed, Long categoryId) {
            var param = new EasyRandomParameters().seed(seed);
            return new EasyRandom(param).nextObject(Product.class);
    }
}