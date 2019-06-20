package com.zhwl.serviceimpl.book;

import com.zhwl.bean.Book;
import com.zhwl.exception.BaseException;
import com.zhwl.mapper.book.BookMapper;
import com.zhwl.property.JWTProperties;
import com.zhwl.service.BookService;
import com.zhwl.txlcn.tc.annotation.DTXPropagation;
import com.zhwl.txlcn.tc.annotation.LcnTransaction;
import com.zhwl.txlcn.tc.annotation.TxcTransaction;
import com.zhwl.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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

    @Autowired
    private JWTProperties jWTProperties;

    @Override
    public Integer add(Book book) {
       /* if (true)
            throw new BaseException(0,"出现异常了");*/
        book.setId(UuidUtil.get32UUID());
        bookMapper.insert(book);
        return 1;
    }

    @Override
    public Integer update(Book book) {
        if (StringUtils.isEmpty(book.getId()))
            throw new BaseException(0,"参数异常");
        return bookMapper.updateById(book);
    }

    @Override
    public List<Book> getAll() {
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
    public Book getById(String id) {
        return bookMapper.selectById(id);
    }

    @Override
    public List<Book> getByName(String name) {
        return Arrays.asList(
                new Book("1",name,25.00,10),
                new Book("2",name,35.00,20),
                new Book("3",name,45.00,30));
    }

    //减少库存
    @LcnTransaction
    //@TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @Override
    public void reduceBook(String bookId, Integer count) {

        if(bookMapper.reduceBook(bookId,count) > 0 )
            System.out.println("书籍"+bookId+"减少了"+count);
        else
            throw new BaseException(0,"书籍"+bookId+"库存不足！");

        /*Book book = this.getById(bookId);
        int stock = book.getStock() - count;
        if ( stock > 0)
            book.setStock(stock);
        else
            throw new BaseException("库存不足！");
        this.update(book);*/
    }

    @Override
    public JWTProperties getJWT() {
        return jWTProperties;
    }
}
