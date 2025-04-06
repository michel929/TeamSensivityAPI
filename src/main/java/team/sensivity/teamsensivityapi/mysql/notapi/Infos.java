package team.sensivity.teamsensivityapi.mysql.notapi;

import team.sensivity.teamsensivityapi.mysql.Connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Infos {
    public static String getBotInfos(String row){
        String url = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM bot";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                url = rs.getString(row);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
