package team.sensivity.teamsensivityapi.api;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import team.sensivity.teamsensivityapi.mysql.UserInfos;
import team.sensivity.teamsensivityapi.objects.UserPoints;

@RestController
public class Points {
    @GetMapping("/points/{discordID}")
    public String getPoints(Authentication authentication, @PathVariable String discordID){
        if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SCOPE_ROLE_VERIFIED"))){

            int points = team.sensivity.teamsensivityapi.mysql.Points.getPoints(discordID);
            String username = UserInfos.getUsername(discordID);

            return "{\"discord_id\": \"" + discordID + "\", \"discord_username\": \"" + username + "\", \"points\": \"" + points + "\"}";

        }else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SCOPE_ROLE_DEV"))){

            int points = 19034;
            String discord_id = "1298310381323";
            String username = "DeveloperAccount";

            return "{\"discord_id\": \"" + discord_id + "\", \"discord_username\": \"" + username + "\", \"points\": \"" + points + "\"}";
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @PostMapping(value = "/points/add", consumes = {"application/json"})
    public String addPoints(Authentication authentication, @RequestBody UserPoints userPoints){
        if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SCOPE_ROLE_VERIFIED"))){

            String username = UserInfos.getUsername(userPoints.getDiscordID());
            int oldPoints = team.sensivity.teamsensivityapi.mysql.Points.getPoints(userPoints.getDiscordID());
            int points = userPoints.getPoints() + oldPoints;

            team.sensivity.teamsensivityapi.mysql.Points.addPoints(userPoints.getDiscordID(), userPoints.getPoints());
            return "{\"discord_id\": \"" + userPoints.getDiscordID() + "\", \"discord_username\": \"" + username + "\", \"new_points\": \"" + points + "\", \"old_points\": \"" + oldPoints + "\"}";

        }else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SCOPE_ROLE_DEV"))){

            String discord_id = "1298310381323";
            String username = "DeveloperAccount";
            int oldPoints = 10202322;
            int points = userPoints.getPoints() + oldPoints;
            return "{\"discord_id\": \"" + discord_id + "\", \"discord_username\": \"" + username + "\", \"new_points\": \"" + points + "\", \"old_points\": \"" + oldPoints + "\"}";

        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @PostMapping(value = "/points/remove", consumes = {"application/json"})
    public String removePoints(Authentication authentication, @RequestBody UserPoints userPoints){
        if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SCOPE_ROLE_VERIFIED"))){

            String username = UserInfos.getUsername(userPoints.getDiscordID());
            int oldPoints = team.sensivity.teamsensivityapi.mysql.Points.getPoints(userPoints.getDiscordID());
            int points = oldPoints - userPoints.getPoints();

            team.sensivity.teamsensivityapi.mysql.Points.removePoints(userPoints.getDiscordID(), userPoints.getPoints());
            return "{\"discord_id\": \"" + userPoints.getDiscordID() + "\", \"discord_username\": \"" + username + "\", \"new_points\": \"" + points + "\", \"old_points\": \"" + oldPoints + "\"}";

        }else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SCOPE_ROLE_DEV"))){

            String discord_id = "1298310381323";
            String username = "DeveloperAccount";
            int oldPoints = 10202322;
            int points = oldPoints - userPoints.getPoints();
            return "{\"discord_id\": \"" + discord_id + "\", \"discord_username\": \"" + username + "\", \"new_points\": \"" + points + "\", \"old_points\": \"" + oldPoints + "\"}";

        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
}
