package team.sensivity.teamsensivityapi.mysql;

import team.sensivity.teamsensivityapi.objects.Suspend;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Gameserver {

    public static ArrayList<Suspend> getAllServer(){
        ArrayList<Suspend> sus = new ArrayList<>();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM server";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {

                LocalDate date = LocalDate.parse(rs.getString("start"));
                int days = rs.getInt("laufzeit");

                date = date.plusDays(days);
                boolean suspended = false;

                if(rs.getInt("suspended") == 1){
                    suspended = true;
                }

                Suspend suspend = new Suspend(rs.getInt("server_id"), date, suspended);

                sus.add(suspend);

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sus;
    }

    public static void updateSuspend(int server_id, int suspend){

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE server SET suspended = '" + suspend + "' WHERE server_id = '" + server_id + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteServer(int server_id){

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM server WHERE server_id =" + server_id);

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
