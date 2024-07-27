package team.sensivity.teamsensivityapi.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import team.sensivity.teamsensivityapi.mysql.UserInfos;
import team.sensivity.teamsensivityapi.objects.User;

import java.util.Date;

@RestController()
public class OnlineTime {
    @GetMapping("/online/{discordID}")
    public String getPoints(@PathVariable String discordID, @RequestParam(value = "start", defaultValue = "0", required = false) int start, @RequestParam int anzahl, @RequestParam String token){
        User user = UserInfos.getUserFromToken(token);

        if(user == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "TOKEN UNGUELTIG");
        }else {
            if (user.getGueltig().after(new Date())) {
                if(UserInfos.addRequest(user)) {
                    if (user.getRole().equals("ADMIN") || user.getRole().equals("PERSONAL") || user.getRole().equals("PRODUCTION")) {

                        String username = UserInfos.getUsername(discordID);

                        if(anzahl > 100){
                            anzahl = 100;
                        }

                        JSONArray jsonArray = null;

                        if(start == 0){
                            jsonArray = team.sensivity.teamsensivityapi.mysql.OnlineTime.getOnlineTime(anzahl, discordID);
                        }else {
                            jsonArray = team.sensivity.teamsensivityapi.mysql.OnlineTime.getOnlineTime(anzahl, discordID, start);
                        }

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("online_time", jsonArray);

                        jsonObject.put("discord_username", username);
                        jsonObject.put("discord_id", discordID);

                        return jsonObject.toJSONString();

                    } else if (user.getRole().equals("DEV")) {

                        String discord_id = "1298310381323";
                        String username = "DeveloperAccount";

                        return "{\"discord_id\": \"" + discord_id + "\", \"discord_username\": \"" + username + "\"}";
                    } else {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "KEINE RECHTE");
                    }
                }else {
                    throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "ZU VIELE REQUEST IN DER MINUTE");
                }
            }else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "TOKEN ABGELAUFEN");
            }
        }
    }
}
