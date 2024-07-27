package team.sensivity.teamsensivityapi.mysql;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Roles {
    public static JSONArray getUserRoles(String discord_id){

        JSONArray userRoles = new JSONArray();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM user_role WHERE discord_id = '" + discord_id + "'";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                userRoles.add(getRole(rs.getString("discord_role")));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userRoles;
    }

    public static JSONObject getRole(String role_id){
        JSONObject role = new JSONObject();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM discord_role WHERE role_id = '" + role_id + "'";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {

                role.put("role_id", rs.getString("role_id"));
                role.put("role_name", rs.getString("role_name"));
                role.put("role_color", rs.getString("color"));

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }
}
