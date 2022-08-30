package com.tmax.cm.superstore.category.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column
    @NotNull
    private String categoryName;

    @Column
    @NotNull
    private Long parentId;

    public Category(String categoryName, Long parentId){
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}
