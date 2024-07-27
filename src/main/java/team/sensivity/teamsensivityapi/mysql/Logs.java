package team.sensivity.teamsensivityapi.mysql;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Logs {
    public static JSONArray getLogs(int anzahl){

        JSONArray logs = new JSONArray();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM log_system ORDER BY id DESC LIMIT "+anzahl;
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("discord_id", rs.getString("discord_id"));
                obj.put("discord_tag", rs.getString("discord_tag"));
                obj.put("log_text", rs.getString("log_text"));
                obj.put("date", rs.getString("date"));

                logs.add(obj);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logs;
    }

    public static JSONArray getLogs(int anzahl, int start){

        JSONArray logs = new JSONArray();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM log_system ORDER BY id DESC LIMIT " + anzahl + " OFFSET " + start;;
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("discord_id", rs.getString("discord_id"));
                obj.put("discord_tag", rs.getString("discord_tag"));
                obj.put("log_text", rs.getString("log_text"));
                obj.put("date", rs.getString("date"));

                logs.add(obj);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logs;
    }
}
