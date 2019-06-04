package com.zhwl.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Book {
    private String id;
    private String name;
    private Double price;
    private Integer stock;
}
