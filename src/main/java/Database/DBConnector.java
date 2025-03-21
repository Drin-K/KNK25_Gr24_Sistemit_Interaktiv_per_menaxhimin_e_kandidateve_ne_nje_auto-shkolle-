package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String DB_URL="jdbc:postgresql://localhost/KNK_2025";
    private static final String DB_USER="postgres";
    private static final String DB_USER_PASSWORD="12345678";
    public static Connection getConnection(){
        if (connection==null){
            try {
                connection= DriverManager.getConnection(
                        DB_URL,DB_USER,DB_USER_PASSWORD
                );
            } catch (SQLException e) {
                return null;
            }
        }
        return connection;
}}
