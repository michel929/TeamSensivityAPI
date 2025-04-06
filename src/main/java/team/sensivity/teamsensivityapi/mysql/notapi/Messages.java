package team.sensivity.teamsensivityapi.mysql.notapi;

import team.sensivity.teamsensivityapi.mysql.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Messages {
    public static void createMessage(String discord_id, String titel, String message, String color, String icon){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO messages (discord_id, titel, message, icon, color) VALUES ('"+ discord_id + "', '"+ titel +"' , '"+ message +"', '"+ icon +"', '"+ color +"')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createMessage(String discord_id, String titel, String message, String color, String icon, String link, String link_text){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO messages (discord_id, titel, message, icon, color, link, link_text) VALUES ('"+ discord_id + "', '"+ titel +"' , '"+ message +"', '"+ icon +"', '"+ color +"', '"+ link +"', '"+ link_text +"')");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
