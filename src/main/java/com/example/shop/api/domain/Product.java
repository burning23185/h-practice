package com.example.shop.api.domain;

import com.example.shop.api.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Product extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    private int price;

    private int quantity;

    @Column(length = 500)
    private String introduction = "None";

    @ManyToOne
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    public Product(ProductRequestDto requestDto, Category category){
        this.productName = requestDto.getProductName();
        this.price = requestDto.getPrice();
        this.quantity = requestDto.getQuantity();
        this.introduction = requestDto.getIntroduction();
        this.category = category;
    }

}
