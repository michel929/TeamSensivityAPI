package team.sensivity.teamsensivityapi.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import team.sensivity.teamsensivityapi.mysql.UserInfos;
import team.sensivity.teamsensivityapi.objects.User;
import team.sensivity.teamsensivityapi.objects.UserPoints;

import java.util.Date;

@RestController()
public class Points {
    @GetMapping("/points/{discordID}")
    public String getPoints(@PathVariable String discordID, @RequestParam String token){
        User user = UserInfos.getUserFromToken(token);

        if(user == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "TOKEN UNGUELTIG");
        }else {
            if (user.getGueltig().after(new Date())) {
                if(UserInfos.addRequest(user)) {
                    if (user.getRole().equals("ADMIN") || user.getRole().equals("PERSONAL") || user.getRole().equals("PRODUCTION")) {

                        int points = team.sensivity.teamsensivityapi.mysql.Points.getPoints(discordID);
                        String username = UserInfos.getUsername(discordID);

                        return "{\"discord_id\": \"" + discordID + "\", \"discord_username\": \"" + username + "\", \"points\": \"" + points + "\"}";

                    } else if (user.getRole().equals("DEV")) {

                        int points = 19034;
                        String discord_id = "1298310381323";
                        String username = "DeveloperAccount";

                        return "{\"discord_id\": \"" + discord_id + "\", \"discord_username\": \"" + username + "\", \"points\": \"" + points + "\"}";
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

    @PostMapping(value = "/points/add", consumes = {"application/json"})
    public String addPoints(@RequestBody UserPoints userPoints, @RequestParam String token){
        User user = UserInfos.getUserFromToken(token);

        if(user == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "TOKEN UNGUELTIG");
        }else {
            if(user.getGueltig().after(new Date())) {
                if(UserInfos.addRequest(user)) {
                    if (user.getRole().equals("PRODUCTION") || user.getRole().equals("ADMIN")) {

                        String username = UserInfos.getUsername(userPoints.getDiscordID());
                        int oldPoints = team.sensivity.teamsensivityapi.mysql.Points.getPoints(userPoints.getDiscordID());
                        int points = userPoints.getPoints() + oldPoints;

                        team.sensivity.teamsensivityapi.mysql.Points.addPoints(userPoints.getDiscordID(), userPoints.getPoints(), userPoints.getReason());
                        return "{\"discord_id\": \"" + userPoints.getDiscordID() + "\", \"discord_username\": \"" + username + "\", \"new_points\": \"" + points + "\", \"old_points\": \"" + oldPoints + "\"}";

                    } else if (user.getRole().equals("DEV")) {

                        String discord_id = "1298310381323";
                        String username = "DeveloperAccount";
                        int oldPoints = 10202322;
                        int points = userPoints.getPoints() + oldPoints;
                        return "{\"discord_id\": \"" + discord_id + "\", \"discord_username\": \"" + username + "\", \"new_points\": \"" + points + "\", \"old_points\": \"" + oldPoints + "\"}";
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

    @PostMapping(value = "/points/remove", consumes = {"application/json"})
    public String removePoints(@RequestBody UserPoints userPoints, @RequestParam String token){
            User user = UserInfos.getUserFromToken(token);

        if(user == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "TOKEN UNGUELTIG");
        }else {

            if(user.getGueltig().after(new Date())) {
                if(UserInfos.addRequest(user)) {
                    if (user.getRole().equals("PRODUCTION") || user.getRole().equals("ADMIN")) {

                        String username = UserInfos.getUsername(userPoints.getDiscordID());
                        int oldPoints = team.sensivity.teamsensivityapi.mysql.Points.getPoints(userPoints.getDiscordID());
                        int points = oldPoints - userPoints.getPoints();

                        team.sensivity.teamsensivityapi.mysql.Points.removePoints(userPoints.getDiscordID(), userPoints.getPoints(), userPoints.getReason());
                        return "{\"discord_id\": \"" + userPoints.getDiscordID() + "\", \"discord_username\": \"" + username + "\", \"new_points\": \"" + points + "\", \"old_points\": \"" + oldPoints + "\"}";

                    } else if (user.getRole().equals("DEV")) {

                        String discord_id = "1298310381323";
                        String username = "DeveloperAccount";
                        int oldPoints = 10202322;
                        int points = oldPoints - userPoints.getPoints();
                        return "{\"discord_id\": \"" + discord_id + "\", \"discord_username\": \"" + username + "\", \"new_points\": \"" + points + "\", \"old_points\": \"" + oldPoints + "\"}";
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
