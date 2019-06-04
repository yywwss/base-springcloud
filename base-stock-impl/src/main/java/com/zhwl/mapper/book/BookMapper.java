package com.zhwl.mapper.book;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhwl.bean.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BookMapper extends BaseMapper<Book> {
    List<Book> getAll();
}
