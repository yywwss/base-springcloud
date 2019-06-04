package com.zhwl.serviceimpl.book;

import com.zhwl.bean.Book;
import com.zhwl.exception.BaseException;
import com.zhwl.mapper.book.BookMapper;
import com.zhwl.service.BookService;
import com.zhwl.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("book")
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Integer add(Book book) {
        book.setId(UuidUtil.get32UUID());
        bookMapper.insert(book);
        return 1;
    }

    @Override
    public List<Book> getAll() {
        /*if (true)
            throw new BaseException(0,"出现异常了");*/

        /* 在消费方配置
        *#请求处理的超时时间 ribbon.ReadTimeout=1500
        #请求连接的超时时间 ribbon.ConnectTimeout=2000
        * */
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return bookMapper.getAll();
        /*return Arrays.asList(
                new Book("1","java",25.00,10),
                new Book("2","python",35.00,20),
                new Book("3","Linux",45.00,30));*/
    }

    @Override
    public List<Book> getByName(String name) {
        return Arrays.asList(
                new Book("1",name,25.00,10),
                new Book("2",name,35.00,20),
                new Book("3",name,45.00,30));
    }
}
