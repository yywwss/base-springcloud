package com.zhwl.service.book;

import com.zhwl.bean.Book;

import java.util.List;

public interface BookService {

    Integer add(Book book);

    List<Book> getAll();
}
