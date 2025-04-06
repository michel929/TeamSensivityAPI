package team.sensivity.teamsensivityapi.timers;

import team.sensivity.teamsensivityapi.mysql.*;
import team.sensivity.teamsensivityapi.mysql.notapi.Gameserver;
import team.sensivity.teamsensivityapi.mysql.notapi.Infos;
import team.sensivity.teamsensivityapi.mysql.notapi.Messages;
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
                String message = "Dein Server wurde SUSPENDED du hast noch xdays um ihn mit TSP wieder zu aktivieren.";
                switch ((int) ChronoUnit.DAYS.between(suspend.getSuspendedAt(), LocalDate.now())){
                    case 1:
                        message = message.replace("xdays", "fünf Tage");
                        Messages.createMessage(suspend.getDiscord_id(), "Konto bitte aufladen!", message, "#9914fa", Infos.getBotInfos("logo_url"), "https://dashboard.sensivity.team/hosting/dashboard/server-list.php", "Zum Server");
                        break;

                    case 2:
                        message = message.replace("xdays", "vier Tage");
                        Messages.createMessage(suspend.getDiscord_id(), "Konto bitte aufladen!", message, "#9914fa", Infos.getBotInfos("logo_url"), "https://dashboard.sensivity.team/hosting/dashboard/server-list.php", "Zum Server");
                        break;

                    case 3:
                        message = message.replace("xdays", "drei Tage");
                        Messages.createMessage(suspend.getDiscord_id(), "Konto bitte aufladen!", message, "#9914fa", Infos.getBotInfos("logo_url"), "https://dashboard.sensivity.team/hosting/dashboard/server-list.php", "Zum Server");
                        break;

                    case 4:
                        message = message.replace("xdays", "zwei Tage");
                        Messages.createMessage(suspend.getDiscord_id(), "Konto bitte aufladen!", message, "#9914fa", Infos.getBotInfos("logo_url"), "https://dashboard.sensivity.team/hosting/dashboard/server-list.php", "Zum Server");
                        break;

                    case 5:
                        message = message.replace("xdays", "ein Tage");
                        Messages.createMessage(suspend.getDiscord_id(), "Konto bitte aufladen!", message, "#9914fa", Infos.getBotInfos("logo_url"), "https://dashboard.sensivity.team/hosting/dashboard/server-list.php", "Zum Server");
                        break;

                    default:
                        Delete.doDelete("https://panel.sensivity.team/api/application/servers/" + suspend.getId());
                        Gameserver.deleteServer(suspend.getId());
                        System.out.println("Server with ID: " + suspend.getId() + " Deleted");
                        Messages.createMessage(suspend.getDiscord_id(), "Dein Server wurde gelöscht!", "Deiner Server wurde heute gelöscht.", "#9914fa", Infos.getBotInfos("logo_url"));

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
                    for (String user : konto.getUser()){
                        Messages.createMessage(user, "Euer Konto wurde gelöscht!", "Euer Konto wurde heute gelöscht. Ihr könnt zu jeder Zeit nochmal ein neues Konto eröffnen.", "#9914fa", Infos.getBotInfos("logo_url"));
                    }
                    Kontos.deleteKonto(konto.getId());
                }
            }else {
                if(konto.getAmount() < 500) {
                    String message = "Euer Konto mit dem Namen " + konto.getName() + " hat nicht genug TSP aufgeladen um verlängert zu werden. In xdays wird das Konto gelöscht und alle restlichen TSP auf dem Konto verfallen.";
                    switch ((int) ChronoUnit.DAYS.between(konto.getAbgerechnet(), LocalDate.now())) {
                        case 1:
                            for (String user : konto.getUser()){
                                message = message.replace("xdays", "einem Tag");
                                Messages.createMessage(user, "Konto bitte aufladen!", message, "#9914fa", Infos.getBotInfos("logo_url"), "https://dashboard.sensivity.team/banking/konto.php?konto_id=" + konto.getId(), "Zum Konto");
                            }
                            break;

                        case 2:
                            for (String user : konto.getUser()){
                                message = message.replace("xdays", "zwei Tagen");
                                Messages.createMessage(user, "Konto bitte aufladen!", message, "#9914fa", Infos.getBotInfos("logo_url"), "https://dashboard.sensivity.team/banking/konto.php?konto_id=" + konto.getId(), "Zum Konto");
                            }
                            break;

                        case 3:
                            for (String user : konto.getUser()){
                                message = message.replace("xdays", "drei Tagen");
                                Messages.createMessage(user, "Konto bitte aufladen!", message, "#9914fa", Infos.getBotInfos("logo_url"), "https://dashboard.sensivity.team/banking/konto.php?konto_id=" + konto.getId(), "Zum Konto");
                            }
                            break;

                        default:
                            break;
                    }
                }
            }
        }
    }
}
