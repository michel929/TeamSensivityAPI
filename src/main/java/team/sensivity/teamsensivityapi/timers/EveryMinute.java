package team.sensivity.teamsensivityapi.timers;

import team.sensivity.teamsensivityapi.mysql.UserInfos;

import java.util.TimerTask;

public class EveryMinute extends TimerTask {

    @Override
    public void run() {
        //ResetRequestPerMinute
        UserInfos.requestPerMinute.clear();
    }
}
