package com.example.shop.api.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(length = 30, nullable = false)
    private String categoryName;

    @Column(nullable = true)
    private String parentPath;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    public Category(String categoryName, String parentPath) {
        this.categoryName = categoryName;
        this.parentPath = parentPath;
    }

    public void updateCategory(String categoryName, String parentPath){
        this.categoryName = categoryName;
        this.parentPath = parentPath;
    }

    public String getCategoryPathName(){

        if (this.parentPath == null) return getCategoryName();
        StringBuilder sb = new StringBuilder();
        sb.append(getParentPath()).append(" < ").append(getCategoryName());
        return sb.toString();
    }
}

