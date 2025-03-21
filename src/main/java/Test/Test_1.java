package Test;

import Database.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
public class Test_1 {
    public static void main(String[] args)throws SQLException {
        Connection connection = DBConnector.getConnection();
        if(connection.isValid(100)){
            System.out.println("Db Connected!!!");
        }
    }
}
