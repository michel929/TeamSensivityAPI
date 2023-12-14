package team.sensivity.teamsensivityapi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping("/test")
    public String home(Principal principal){
        System.out.println();
        return "TEST " + principal.getName();
    }

    @GetMapping("/admin/test")
    public String admin(Authentication authentication){

        if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SCOPE_ROLE_ADMIN"))){
            return "ADMINTEST";
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
