package team.sensivity.teamsensivityapi.objects;

public class UserPoints {

    private int points;
    private String discordID;
    private String reason;

    public UserPoints(String discordID, int points, String reason){
        this.discordID = discordID;
        this.points = points;
        this.reason = reason;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDiscordID() {
        return discordID;
    }

    public void setDiscordID(String discordID) {
        this.discordID = discordID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
