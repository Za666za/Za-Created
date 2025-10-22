package aopStudy.transaction;

import java.sql.*;
//
//public class DbUtil {
//    static String driver="com.mysql.jdbc.Driver";
//    static String url="jdbc:mysql://localhost:3306/bims?characterEncoding=utf-8";
//    static String user="root";
//    static String pwd="root";
//    public static Connection conn=null;
//    static Statement sm=null;
//    ResultSet rs=null;
//    static {
//        try {
//            Class.forName(driver);
//            conn= DriverManager.getConnection(url,user,pwd);
//            sm=conn.createStatement();
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//    public ResultSet getResult(String sql) {
//        try {
//            rs=sm.executeQuery(sql);
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return rs;
//    }
//    public void executeDML(String sql) throws SQLException {
//        sm.executeUpdate(sql);
//
//    }
//    public void closeDb() {
//        try {
//            if(rs!=null)
//                rs.close();
//            if(sm!=null)
//                sm.close();
//            if(conn!=null)
//                conn.close();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    protected Connection conn;

    public DbUtil() {
        try {
            // 请根据你的数据库配置修改 URL、用户名和密码
            String url = "jdbc:mysql://localhost:3306/aop_demo?useSSL=false&serverTimezone=UTC";
            String username = "root";
            String password = "1593574268nM"; // <-- 修改为你的密码

            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接创建成功！");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接失败！", e);
        }
    }
}
