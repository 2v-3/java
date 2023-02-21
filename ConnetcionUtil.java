package cn.rrl.mvc1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnetcionUtil {
        //准备数据库相关的信息
        //数据库的连接信息
        private static final String URL = "jdbc:mysql://localhost:3306/demo?useSSL=true&useUnicode=true&characterEncoding=UTF-8";
        //驱动名称
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        //用户名
        private static final String USERNAME = "root";
        //密码
        private static final String PASSWORD = "root";

        //加载驱动信息到程序中
        static {//程序一旦启动就会自动执行静态代码块中的内容，不需要像方法执行一样需要调用方法名称
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //获取连接对象的方法
        public static Connection getConnection(){
            try {
                return DriverManager.getConnection(URL,USERNAME,PASSWORD);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
        //关闭连接的方法
    public static void close(Connection connection){
        try {
            if(connection!=null&&!connection.isClosed())
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //关闭连接重载的方法
    public static void close(Connection connection, PreparedStatement pst){
        try {
            if(connection!=null&&!connection.isClosed()) {
                connection.close();
            }
            if (pst!=null&&!pst.isClosed())
            pst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
