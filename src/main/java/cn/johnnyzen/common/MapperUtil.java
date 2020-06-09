package cn.johnnyzen.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.util.Assert;

import java.io.Reader;

/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: HiyuSite
 * @author: 千千寰宇
 * @date: 2020/5/26  17:36:52
 * @description: ...
 */

public class MapperUtil {
    private final static String MU_MYBATIS_CONFIG_FILE_PATH = "db/mybatis-config.xml";
    private static SqlSessionFactory MU_SQL_SESSION_FACTORY;;
    private static Reader MU_READER;

    static {
        try {
            MU_READER = Resources.getResourceAsReader(MU_MYBATIS_CONFIG_FILE_PATH);//step0 借java.io.Reader加载MyBatis全局配置文件
            MU_SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(MU_READER);//step1 借SqlSessionFactoryBuilder构造SqlSessionFactory
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return MU_SQL_SESSION_FACTORY;
    }

    public static SqlSession getSqlSession(boolean isOpenTransaction){
        if(isOpenTransaction==true)
            return getSqlSessionFactory().openSession(); //开启事务 (关闭自动提交)
        else {
            return getSqlSessionFactory().openSession(false); //关闭事务 (开启自动提交)
        }
    }
}
