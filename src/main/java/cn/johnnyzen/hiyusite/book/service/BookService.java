package cn.johnnyzen.hiyusite.book.service;

import cn.johnnyzen.common.pager.Page;
import cn.johnnyzen.hiyusite.book.domain.Book;

import java.util.List;

/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: SSMDemo2
 * @author: 千千寰宇
 * @date: 2020/6/6  01:27:19
 * @description: ...
 */

public interface BookService {
    List<Book> findAll();

    Page<Book> userBookList(Integer userId, int curPage, int pageSize);
}
