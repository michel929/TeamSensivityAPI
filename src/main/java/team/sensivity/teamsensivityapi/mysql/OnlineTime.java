package team.sensivity.teamsensivityapi.mysql;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OnlineTime {

    public static JSONArray getOnlineTime(int anzahl, String discord_id){

        JSONArray online_time = new JSONArray();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM online WHERE discord_id = '" + discord_id + "' ORDER BY id DESC LIMIT " + anzahl;
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("join_date", rs.getString("firstDate"));
                obj.put("leave_date", rs.getString("secondDate"));
                obj.put("minuten", rs.getString("minuten"));

                online_time.add(obj);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return online_time;
    }

    public static JSONArray getOnlineTime(int anzahl, String discord_id, int start){

        JSONArray online_time = new JSONArray();

        System.out.println(start);

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM online WHERE discord_id = '" + discord_id + "' ORDER BY id DESC LIMIT " + anzahl + " OFFSET " + start;
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("join_date", rs.getString("firstDate"));
                obj.put("leave_date", rs.getString("secondDate"));
                obj.put("minuten", rs.getString("minuten"));

                online_time.add(obj);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return online_time;
    }
}
