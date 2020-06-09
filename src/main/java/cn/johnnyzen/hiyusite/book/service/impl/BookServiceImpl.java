package cn.johnnyzen.hiyusite.book.service.impl;

import cn.johnnyzen.common.pager.Page;
import cn.johnnyzen.hiyusite.book.domain.Book;
import cn.johnnyzen.hiyusite.book.mapper.BookMapper;
import cn.johnnyzen.hiyusite.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: SSMDemo2
 * @author: 千千寰宇
 * @date: 2020/6/6  01:28:41
 * @description: ...
 */
@Service("bookServiceImpl")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.findAll();
    }


    @Override
    public Page<Book> userBookList(Integer userId, int curPage, int pageSize) {
        Page<Book> page = new Page<Book>(curPage, pageSize);
        page.setTotalCount(bookMapper.countTotalFindBooksByUserId(userId));//唯二的setter
        page.setDataSet( bookMapper.findBooksByUserId(userId, page.getOffset(), page.getPageSize()) );//唯二的setter
        return page;
    }
}
