
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author weiwei
 * @Date 2020-11-15 18:03
 * @description
 **/
public class JDBCTest {


    @Test
    public void test() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","root");
        Statement statement =connection.createStatement();
        statement.execute("insert into user (username,phone,code) values ('a','13312341234','1122')");
        statement.execute("update user set username = 'b' where code = '1122'");
        ResultSet resultSet = statement.executeQuery("select * from user");
        if(resultSet.next()){
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString(4));
        }
        statement.execute("delete from  user where code = '1122'");
        resultSet.close();
        statement.close();
        connection.close();
    }
    @Test
    public void test2() {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement =null;
        PreparedStatement preparedStatement1 =null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","root");
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("insert into user (username,phone,code) values (?,?,?)");
            preparedStatement.setString(1,"a");
            preparedStatement.setString(2,"13312341234");
            preparedStatement.setString(3,"1122");
            preparedStatement.execute();
            preparedStatement1 = connection.prepareStatement("update user set username = ? where code = ?");
            preparedStatement1.setString(1,"b");
            preparedStatement1.setString(2,"1122");
            preparedStatement1.execute();
            preparedStatement3 = connection.prepareStatement("select * from user where code = ?");
            preparedStatement3.setString(1,"1122");
             resultSet = preparedStatement3.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
            }
            preparedStatement4 = connection.prepareStatement("delete from  user where code = ?");
            preparedStatement4.setString(1,"1122");
            preparedStatement4.execute();
            resultSet.close();
            preparedStatement.close();
            preparedStatement1.close();
            preparedStatement3.close();
            preparedStatement4.close();

            connection.commit();//try的最后提交事务
        }catch (Exception e){
            if(connection != null){
                try {
                    connection.rollback();
                }catch (Exception e1){
                    e.printStackTrace();
                }
            }
        }finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement1 != null){
                try {
                    preparedStatement1.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement3 != null){
                try {
                    preparedStatement3.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement4 != null){
                try {
                    preparedStatement4.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void test3(){
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");

        HikariDataSource hikariDataSource=new HikariDataSource(hikariConfig);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection= hikariDataSource.getConnection();
            statement = connection.createStatement();
             resultSet = statement.executeQuery("select * from user");
            if(resultSet.next()){
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
