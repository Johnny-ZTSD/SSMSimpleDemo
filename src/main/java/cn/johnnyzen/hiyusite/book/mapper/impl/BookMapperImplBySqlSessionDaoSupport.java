package cn.johnnyzen.hiyusite.book.mapper.impl;

import cn.johnnyzen.hiyusite.book.domain.Book;
import cn.johnnyzen.hiyusite.book.mapper.BookMapper;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: SSMDemo2
 * @author: 千千寰宇
 * @date: 2020/6/6  01:19:27
 * @description: SqlSessionDaoSupport: 对 SqlSessionTemplate 的封装，内部含 getSqlSession()方法
 */

public class BookMapperImplBySqlSessionDaoSupport extends SqlSessionDaoSupport implements BookMapper {
/*    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;*/

    @Override
    public List<Book> findAll() {
        return getSqlSession().getMapper(BookMapper.class).findAll();
    }

    @Override
    public List<Book> findBooksByUserId(Integer userId,int offset,int pageSize) {
        return getSqlSession().getMapper(BookMapper.class).findBooksByUserId(userId, offset, pageSize);
    }

    @Override
    public int countTotalFindBooksByUserId(Integer userId) {
        return getSqlSession().getMapper(BookMapper.class).countTotalFindBooksByUserId(userId);
    }

/*    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }*/
}
