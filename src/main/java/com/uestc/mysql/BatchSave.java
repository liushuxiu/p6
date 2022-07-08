package com.uestc.mysql;


import java.sql.*;
import java.util.Random;



public class BatchSave {
    private static String url = "jdbc:mysql://120.48.45.63:3306/test?rewriteBatchedStatements=true&characterEncoding=utf8";
    private static String user = "root";
    private static String password = "123456";    //换成你自己的

    public static void main(String[] args) {
        Test();
    }

    public static void Test(){
        Connection conn = null;
        PreparedStatement pstm =null;
        ResultSet rt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO user(name,age,phone,address) VALUES(?,?,?,?)";
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            conn.setAutoCommit(false);  //设置事务不自动提交
            Long startTime = System.currentTimeMillis();
            Random rand = new Random();
            int a,b,c,d;
            for (int i = 1; i <= 100000; i++) {
                pstm.setString(1, "阿弥陀佛"+i);
                pstm.setInt(2, 20+rand.nextInt(10));
                a = rand.nextInt(10);
                b = rand.nextInt(10);
                c = rand.nextInt(10);
                d = rand.nextInt(10);
                pstm.setString(3, "188"+a+"88"+b+c+"66"+d);
                pstm.setString(4, "xxxxxxxxxx_"+"188"+a+"88"+b+c+"66"+d);
                pstm.addBatch();
            }
            System.out.println("ssss");
            pstm.executeBatch();   //批量执行
            conn.commit();    //事务提交
            Long endTime = System.currentTimeMillis();
            System.out.println("OK,用时：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            if(pstm!=null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

