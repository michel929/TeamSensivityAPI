package team.sensivity.teamsensivityapi.mysql;

import java.sql.*;

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

    public static void addPoints(String id, int points, String reason){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE user SET points = '" + points + "' WHERE discord_id = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO points (discord_id, type, points, grund) VALUES ('"+ id + "', 1 , '"+ points +"', '"+ reason +"')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removePoints(String id, int points, String reason){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE user SET points = '" + points + "' WHERE discord_id = '" + id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO points (discord_id, type, points, grund) VALUES ('"+ id + "', 0 , '"+ points +"', '"+ reason +"')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
