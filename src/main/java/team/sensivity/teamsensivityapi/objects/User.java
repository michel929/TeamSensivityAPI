package team.sensivity.teamsensivityapi.objects;

import java.sql.Timestamp;

public class User {
    private String discordID;
    private String roles;
    private String token;
    private Timestamp gueltig;
    private int requests;

    public User(String discordID, String roles, String token, Timestamp gueltig, int requests) {
        this.discordID = discordID;
        this.roles = roles;
        this.token = token;
        this.gueltig = gueltig;
        this.requests = requests;
    }


    public String getDiscordID() {
        return discordID;
    }

    public void setDiscordID(String discordID) {
        this.discordID = discordID;
    }

    public String getRole() {
        return roles;
    }

    public void setRole(String role) {
        this.roles = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getGueltig() {
        return gueltig;
    }

    public void setGueltig(Timestamp gueltig) {
        this.gueltig = gueltig;
    }

    public int getRequests() {
        return requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }
}
