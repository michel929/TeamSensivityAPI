package team.sensivity.teamsensivityapi.timers;

import team.sensivity.teamsensivityapi.mysql.Gameserver;
import team.sensivity.teamsensivityapi.mysql.Kontos;
import team.sensivity.teamsensivityapi.mysql.Points;
import team.sensivity.teamsensivityapi.mysql.UserInfos;
import team.sensivity.teamsensivityapi.objects.Konto;
import team.sensivity.teamsensivityapi.objects.Suspend;
import team.sensivity.teamsensivityapi.request.Delete;
import team.sensivity.teamsensivityapi.request.Post;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.TimerTask;

public class EveryMinute extends TimerTask {

    @Override
    public void run() {
        //ResetRequestPerMinute
        UserInfos.requestPerMinute.clear();

        //SuspendServer
        ArrayList<Suspend> suspends = Gameserver.getAllServer();

        for (Suspend suspend : suspends) {
            if(!suspend.isSuspended()){
                LocalDate now = LocalDate.now();

                if(now.isAfter(suspend.getSuspendedAt())){
                    Gameserver.updateSuspend(suspend.getId(), 1);

                    Post.doPost("https://panel.sensivity.team/api/application/servers/" + suspend.getId() + "/suspend");

                    System.out.println("Server with ID: " + suspend.getId() + " Suspended at " + suspend.getSuspendedAt());
                }
            }else {

                switch ((int) ChronoUnit.DAYS.between(suspend.getSuspendedAt(), LocalDate.now())){
                    case 1:
                        //ERINNERUNG
                        break;

                    case 2:
                        //ERINNERUNG2
                        break;

                    case 3:
                        break;

                    case 4:
                        break;

                    case 5:
                        break;

                    default:
                        Delete.doDelete("https://panel.sensivity.team/api/application/servers/" + suspend.getId());
                        Gameserver.deleteServer(suspend.getId());
                        System.out.println("Server with ID: " + suspend.getId() + " Deleted");
                        break;
                }
            }
        }


        //Kontopreis
        ArrayList<Konto> kontos = Kontos.getAllKontos();

        for (Konto konto : kontos) {
            if(konto.getAbgerechnet().isBefore(LocalDate.now())){
                if(konto.getAmount() >= 500){
                    System.out.println("500 Points werden von " + konto.getId() + " abgezogen.");
                    Kontos.abbuchung(konto);
                }else {
                    System.out.println("Konto mit der ID: " + konto.getId() + " wird gelöscht.");
                    Kontos.deleteKonto(konto.getId());
                }
            }else {
                if(konto.getAmount() < 500) {
                    switch ((int) ChronoUnit.DAYS.between(konto.getAbgerechnet(), LocalDate.now())) {
                        case 1:
                            //ERINNERUNG
                            break;

                        case 2:
                            //ERINNERUNG2
                            break;

                        case 3:
                            break;

                        default:
                            break;
                    }
                }
            }
        }
    }
}
