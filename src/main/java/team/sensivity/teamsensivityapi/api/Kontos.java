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
import team.sensivity.teamsensivityapi.objects.Konto;
import team.sensivity.teamsensivityapi.objects.User;

import java.util.ArrayList;
import java.util.Date;

@RestController()
public class Kontos {
    @GetMapping("/kontos/{kontoID}")
    public String getKonto(@PathVariable String kontoID, @RequestParam String token){
        User user = UserInfos.getUserFromToken(token);

        if(user == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "TOKEN UNGUELTIG");
        }else {
            if (user.getGueltig().after(new Date())) {
                if(UserInfos.addRequest(user)) {
                    if (user.getRole().equals("ADMIN") || user.getRole().equals("PERSONAL") || user.getRole().equals("PRODUCTION")) {

                        int konto_id = 0;

                        try {
                            konto_id = Integer.parseInt(kontoID);
                        }catch (Exception e){
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "KONTO ID IST KEINE ZAHL");
                        }

                        Konto konto = team.sensivity.teamsensivityapi.mysql.Kontos.getKonto(konto_id);


                        if(konto == null){
                            return "[]";
                        }

                        JSONArray jsonArray = new JSONArray();

                        for (String us : konto.getUser()){
                            jsonArray.add(us);
                        }

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("user", jsonArray);

                        jsonObject.put("abgerechnet", konto.getAbgerechnet());
                        jsonObject.put("iban", konto.getIban());
                        jsonObject.put("TSP", konto.getAmount());
                        jsonObject.put("name", konto.getName());
                        jsonObject.put("id", konto.getId());

                        return jsonObject.toJSONString();

                    } else if (user.getRole().equals("DEV")) {

                        int points = 19034;
                        String discord_id = "1298310381323";
                        String username = "DeveloperAccount";
//TODO
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

    @GetMapping("/kontos")
    public String getKontos(@RequestParam String token){
        User user = UserInfos.getUserFromToken(token);

        if(user == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "TOKEN UNGUELTIG");
        }else {
            if (user.getGueltig().after(new Date())) {
                if(UserInfos.addRequest(user)) {
                    if (user.getRole().equals("ADMIN") || user.getRole().equals("PERSONAL") || user.getRole().equals("PRODUCTION")) {

                        ArrayList<Konto> konto = team.sensivity.teamsensivityapi.mysql.Kontos.getAllKontos();


                        if(konto.isEmpty()){
                            return "[]";
                        }

                        JSONArray konto_list = new JSONArray();

                        for (Konto konto1 : konto) {
                            JSONArray jsonArray = new JSONArray();

                            for (String us : konto1.getUser()){
                                jsonArray.add(us);
                            }

                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("user", jsonArray);

                            jsonObject.put("abgerechnet", konto1.getAbgerechnet());
                            jsonObject.put("iban", konto1.getIban());
                            jsonObject.put("TSP", konto1.getAmount());
                            jsonObject.put("name", konto1.getName());
                            jsonObject.put("id", konto1.getId());

                            konto_list.add(jsonObject);
                        }

                        return konto_list.toJSONString();

                    } else if (user.getRole().equals("DEV")) {

                        int points = 19034;
                        String discord_id = "1298310381323";
                        String username = "DeveloperAccount";
                        //TODO
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
}
