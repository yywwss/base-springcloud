package com.zhwl.serviceimpl.book;

import com.zhwl.bean.Book;
import com.zhwl.exception.BaseException;
import com.zhwl.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("book")
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

        /* 在消费方配置
        *#请求处理的超时时间 ribbon.ReadTimeout=1500
        #请求连接的超时时间 ribbon.ConnectTimeout=2000
        * */
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return Arrays.asList(
                new Book("1","java",25.00,10),
                new Book("2","python",35.00,20),
                new Book("3","Linux",45.00,30));
    }
}
