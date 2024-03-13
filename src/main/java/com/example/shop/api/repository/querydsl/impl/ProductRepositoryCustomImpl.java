package com.example.shop.api.repository.querydsl.impl;

import com.example.shop.api.domain.Product;
import com.example.shop.api.repository.querydsl.ProductRepositoryCustom;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.shop.api.domain.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Product> getProductPage(Pageable pageable) {
        JPAQuery<Product> query = jpaQueryFactory
                .selectFrom(product)
                .orderBy(productSort(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());


        List<Product> content = query.fetch();
        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(product.count())
                .from(product);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private OrderSpecifier<?> productSort(Pageable page) {
        //서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
        if (!page.getSort().isEmpty()) {
            //정렬값이 들어 있으면 for 사용하여 값을 가져온다
            for (Sort.Order order : page.getSort()) {
                // 서비스에서 넣어준 DESC or ASC 를 가져온다.
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                // 서비스에서 넣어준 정렬 조건을 스위치 케이스 문을 활용하여 셋팅하여 준다.
                switch (order.getProperty()){
                    case "productName":
                        return new OrderSpecifier(direction, product.productName);
                    case "price":
                        return new OrderSpecifier(direction, product.price);
                    case "createAt":
                        return new OrderSpecifier(direction, product.createdAt);
                }
            }
        }
        return new OrderSpecifier(Order.DESC, product.createdAt);
    }
}
