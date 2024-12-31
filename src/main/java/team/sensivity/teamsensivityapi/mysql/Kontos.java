package team.sensivity.teamsensivityapi.mysql;

import team.sensivity.teamsensivityapi.objects.Konto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Kontos {
    public static ArrayList<Konto> getAllKontos(){
        ArrayList<Konto> sus = new ArrayList<>();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM kontos";
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {

                LocalDate date = LocalDate.parse(rs.getString("abgerechnet"));
                date = date.plusMonths(1);

                ArrayList<String> users = getKontoUser(rs.getInt("id"));

                Konto konto = new Konto(rs.getInt("id"), rs.getString("name"), rs.getInt("amount"), date, users);

                sus.add(konto);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sus;
    }


    public static ArrayList<String> getKontoUser(int id){
        ArrayList<String> sus = new ArrayList<>();

        try {
            Connection con = Connect.getConnection();
            String sql = "SELECT * FROM konto_user WHERE konto_id = " + id;
            Statement stmt  = con.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                sus.add(rs.getString("discord_id"));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sus;
    }

    public static void abbuchung(Konto konto){

        int amount = konto.getAmount() - 500;

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("UPDATE kontos SET amount = '" + amount + "',abgerechnet = '" + LocalDate.now() + "'  WHERE id = '" + konto.getId() + "'");

            posted.executeUpdate();
            con.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("INSERT INTO konto_verlauf (konto_id, type, points, discord_id) VALUES ('"+ konto.getId() + "', 0 , 500, -1)");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteKonto(int konto_id){
        try {
            Connection con = Connect.getConnection();

            PreparedStatement posted = con.prepareStatement("DELETE FROM kontos WHERE id = '" + konto_id +"'");

            posted.executeUpdate();

            posted = con.prepareStatement("DELETE FROM konto_user WHERE konto_id = '" + konto_id +"'");

            posted.executeUpdate();

            posted = con.prepareStatement("DELETE FROM konto_verlauf WHERE konto_id = '" + konto_id +"'");

            posted.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
