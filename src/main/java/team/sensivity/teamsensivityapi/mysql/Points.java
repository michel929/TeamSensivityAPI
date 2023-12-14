package team.sensivity.teamsensivityapi.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Points {
    public static int getPoints(String id){
        int points = 0;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM users";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("discord_id"))){
                    points = rs.getInt("points");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return points;
    }

    public static void addPoints(String id, int points){

    }

    public static void removePoints(String id, int points){

    }
}
