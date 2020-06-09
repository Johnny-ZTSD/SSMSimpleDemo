package kingbase;

/**
 * @author: 千千寰宇
 * @date: 2020/5/27  16:14:18
 * @description: ...
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class KingBaseTest {
    public static final String DRIVER = "com.kingbase8.Driver";
    public static final String URL = "jdbc:kingbase8://127.0.0.1:123456/hellodb";
    public static final String USER = "hellodb";
    public static final String PASSWORD = "hellodb";

    public static void main(String[] args) throws Exception {
        //1.加载驱动程序 com.mysql.jdbc.Driver
        Class.forName(DRIVER);
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT 'ABC9079' LIKE CONCAT('A','%') AS a;");
        //如果有数据，rs.next()返回true
        while(rs.next()){
//            System.out.println("result:"+rs.getString("a"));
            System.out.println(rs.getObject(1));
        }
    }
}
