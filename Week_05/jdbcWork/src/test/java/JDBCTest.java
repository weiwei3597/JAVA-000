
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
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
        ResultSet resultSet = statement.executeQuery("select * from user");
        if(resultSet.next()){
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString(4));
        }

    }
}
