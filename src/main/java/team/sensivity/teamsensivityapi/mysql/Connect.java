package team.sensivity.teamsensivityapi.mysql;

import team.sensivity.teamsensivityapi.geheim.DBPassword;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    static String url = "jdbc:mysql://192.168.178.200:3306/TeamSensivity";
    static String user = "api";
    static String pass = DBPassword.dataPasswort;

    public static Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
