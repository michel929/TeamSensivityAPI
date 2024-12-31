package team.sensivity.teamsensivityapi.objects;

import java.time.LocalDate;

public class Suspend {

    private int id;
    private LocalDate suspendedAt;
    private boolean suspended;

    public Suspend(int id, LocalDate suspendedAt, boolean suspended) {
        this.id = id;
        this.suspendedAt = suspendedAt;
        this.suspended = suspended;
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
}
