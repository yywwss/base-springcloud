package com.zhwl.mapper.book;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhwl.bean.Book;
import com.zhwl.plugin.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BookMapper extends BaseMapper<Book> {
    List<Book> getAll();

    Integer reduceBook(@Param("id") String bookId, @Param("count") Integer count);

    List<Book> getByPage(Page page);
}
