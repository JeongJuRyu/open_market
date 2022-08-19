package com.tmax.cm.superstore.category.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    private Long parentId;

    public Category(String categoryName, Long parentId){
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}
