package com.zhwl.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)
@TableName("t_book")
public class Book {
    private String id;
    private String name;
    private Double price;
    private Integer stock;
}
