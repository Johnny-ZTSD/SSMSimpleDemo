package cn.johnnyzen.hiyusite.book.mapper.impl;

import cn.johnnyzen.hiyusite.book.domain.Book;
import cn.johnnyzen.hiyusite.book.mapper.BookMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: SSMDemo2
 * @author: 千千寰宇
 * @date: 2020/6/6  11:06:01
 * @description: ...
 */

public class BookMapperImpl implements BookMapper {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Book> findAll() {
        return sqlSession.getMapper(BookMapper.class).findAll();
    }

    @Override
    public List<Book> findBooksByUserId(Integer userId, int offset, int pageSize) {
        return sqlSession.getMapper(BookMapper.class).findBooksByUserId(userId, offset, pageSize);
    }

    @Override
    public int countTotalFindBooksByUserId(Integer userId) {
        return sqlSession.getMapper(BookMapper.class).countTotalFindBooksByUserId(userId);
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
