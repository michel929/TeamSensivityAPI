package team.sensivity.teamsensivityapi.mysql;

import team.sensivity.teamsensivityapi.objects.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ConcurrentHashMap;

public class UserInfos {

    public static ConcurrentHashMap<String, Integer> requestPerMinute = new ConcurrentHashMap<>();

    public static boolean addRequest(User user){
        if(requestPerMinute.containsKey(user.getDiscordID())){
            requestPerMinute.put(user.getDiscordID(), requestPerMinute.get(user.getDiscordID()) + 1);
        }else {
            requestPerMinute.put(user.getDiscordID(), 1);
        }

        if(user.getRequests() >= requestPerMinute.get(user.getDiscordID())){
            return true;
        }else {
            return false;
        }
    }

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

    public static User getUserFromToken(String token){
        User user = null;

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM api WHERE token = '" + token + "'";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                user = new User(rs.getString("discord_ID"), rs.getString("rolle"), rs.getString("token"), rs.getTimestamp("datum"), rs.getInt("requests"));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void createUser(){

    }

    public static void deleteUser(String id){

    }
}
