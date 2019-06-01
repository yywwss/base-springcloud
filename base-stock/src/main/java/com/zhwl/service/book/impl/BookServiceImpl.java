package com.zhwl.service.book.impl;

import com.zhwl.exception.BaseException;
import com.zhwl.service.book.BookService;
import com.zhwl.bean.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Override
    public Integer add(Book book) {
        System.out.println(book);
        return 1;
    }

    @Override
    public List<Book> getAll() {
        /*if (true)
            throw new BaseException(0,"出现异常了");*/
        return Arrays.asList(
                new Book("1","java",25.00,10),
                new Book("2","python",35.00,20),
                new Book("3","Linux",45.00,30));
    }
}
