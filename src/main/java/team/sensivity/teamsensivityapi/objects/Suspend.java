package team.sensivity.teamsensivityapi.objects;

import java.time.LocalDate;

public class Suspend {

    private int id;
    private LocalDate suspendedAt;
    private boolean suspended;
    private String discord_id;

    public Suspend(int id, LocalDate suspendedAt, boolean suspended, String discordId) {
        this.id = id;
        this.suspendedAt = suspendedAt;
        this.suspended = suspended;
        discord_id = discordId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getSuspendedAt() {
        return suspendedAt;
    }

    public void setSuspendedAt(LocalDate suspendedAt) {
        this.suspendedAt = suspendedAt;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public String getDiscord_id() {
        return discord_id;
    }

    public void setDiscord_id(String discord_id) {
        this.discord_id = discord_id;
    }
}
