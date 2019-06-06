package com.zhwl.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)
@TableName("t_order")
public class Order {
    private String id;
    private String bookId;
    private Integer count;

    private Book book;
}
