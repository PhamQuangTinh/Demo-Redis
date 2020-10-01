package com.example.redis.dao;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name ="product")
public class Product extends BaseEntity{

    @Column
    private String productName;

    @Column
    private double unitPrice;

    @Column
    private Long categoryId;
}
