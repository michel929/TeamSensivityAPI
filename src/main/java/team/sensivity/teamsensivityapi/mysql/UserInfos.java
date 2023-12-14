package team.sensivity.teamsensivityapi.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInfos {
    public static String getUsername(String id){
        String username = "";

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM users";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                if(id.equals(rs.getString("discord_id"))){
                    username = rs.getString("discord_username");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return username;
    }
}
