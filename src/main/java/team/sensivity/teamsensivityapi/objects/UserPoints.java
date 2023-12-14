package team.sensivity.teamsensivityapi.objects;

public class UserPoints {

    private int points;
    private String discordID;

    public UserPoints(String discordID, int points){
        this.discordID = discordID;
        this.points = points;
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
}
