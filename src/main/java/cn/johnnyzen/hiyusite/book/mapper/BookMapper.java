package cn.johnnyzen.hiyusite.book.mapper;

import cn.johnnyzen.common.pager.Page;
import cn.johnnyzen.hiyusite.book.domain.Book;

import java.util.List;

/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: SSMDemo2
 * @author: 千千寰宇
 * @date: 2020/6/6  01:13:25
 * @description: ...
 */

public interface BookMapper {
    List<Book> findAll();

    List<Book> findBooksByUserId(Integer userId, int offset, int pageSize);
    int countTotalFindBooksByUserId(Integer userId);
}
