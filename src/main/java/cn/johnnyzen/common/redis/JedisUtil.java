package cn.johnnyzen.common.redis;

/**
 * @IDE Created by IntelliJ IDEA.
 * @Author 千千寰宇(非原作者)
 * @OriginAuthor Roy-Xin(博客园)
 * @Date 2019/6/28  21:05:03
 * @Description jedis工具类(完全摘抄自参考文献1)
 * # 实现Redis操纵(JedisUtil类)所依赖的工具
 * + Redis数据库服务
 *      + [Redis安装](https://www.runoob.com/redis/redis-install.html)
 *      + [Redis 数据类型](https://www.runoob.com/redis/redis-data-types.html)
 * + Jedis(Redis for Java - API)
 *      + jedis是官方首选的java客户端开发包
 *      + Redis不仅是使用命令来操作，现在基本上主流的语言都有客户端支持
 *          + 比如 java、C、C#、C++、php、Node.js、Go等
 *      + 在官方网站里列一些Java的客户端，有Jedis、Redisson、Jredis、JDBC-Redis等
 *          + 其中,官方推荐使用Jedis和Redisson
 *      + 在企业中用的最多的就是Jedis，Jedis同样也是托管在github上
 *      + 地址：https://github.com/xetorthio/jedis
 *      + 下载jedis解压后得到jar包如下：java操作redis数据库API（Jedis）
 * + JedisPool(Jedis连接池)
 * + ResourceBundle(读取属性配置文件jedis.properties)
 *      + Properties与ResourceBundle区别
 *          + 参考文献
 *              + [属性文件操作之Properties与ResourceBundle](https://www.cnblogs.com/tonghun/p/7124245.html)
 *          + 二者都可以读取属性文件中以key/value形式存储的键值对
 *          + ResourceBundle读取属性文件时操作相对简单
 * # 参考文献
 * + [Jedis连接池](https://www.cnblogs.com/xinruyi/p/9391140.html)
 * # 推荐文献
 * + [Jedis常用方法API](https://blog.csdn.net/zhangguanghui002/article/details/78770071)
 */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public final class JedisUtil {
    private static JedisPool jedisPool;
    private static int maxtotal;
    private static int maxwaitmillis;
    private static String host;
    private static int port;
    private static int timeout;
    private static String auth;//密码

    static {
        /*读取 jedis.properties 配置文件*/
        ResourceBundle rb = ResourceBundle.getBundle("jedis");
        maxtotal = Integer.parseInt(rb.getString("maxtotal"));
        maxwaitmillis = Integer.parseInt(rb.getString("maxwaitmillis"));
        host = rb.getString("host");
        port = Integer.parseInt(rb.getString("port"));
        auth = rb.getString("auth");
        timeout = Integer.parseInt(rb.getString("timeout"));

        /*创建连接池*/
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxtotal);
        jedisPoolConfig.setMaxWaitMillis(maxwaitmillis);
        jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, auth);
    }

    private JedisUtil() {

    }
    /*获取jedis*/
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 根据指定的分数据库索引[dabaseIndex]，获取Redis分数据库
     * + 参考文献
     *   + [Redis 分库](https://www.cnblogs.com/DillGao/p/8494710.html)
     * + Redis 可以分库，相当于 MySQL 中的 database。
     * + 控制数据库总数在 redis配置文件中设置，默认是 16 个。
     * + 数据库名称是整数索引标识，而不是由一个数据库名称字符串。
     * + 选择数据库用 select 命令: redis>select 2
     * + reidis 中的操作，默认是 数据库 0；
     * + 每个数据库都有属于自己的空间，不必担心数据库之间的key冲突。
     * @param dabaseIndex
     * @return
     */
    public static Jedis getJedis(int dabaseIndex){
        Jedis jedis = jedisPool.getResource();
        jedis.select(dabaseIndex);
        return jedis;
    }

    /*关闭Jedis*/
    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    public static void main(String[] args) {
        Jedis jedis = JedisUtil.getJedis();
        String key = "love";
        System.out.println(key+": " + jedis.get(key));
        JedisUtil.close(jedis);//释放资源
    }
}

