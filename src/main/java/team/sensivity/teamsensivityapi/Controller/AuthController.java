package team.sensivity.teamsensivityapi.Controller;

import team.sensivity.teamsensivityapi.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/admin/token/{token}{id}")
    public String token(@PathVariable String id, @PathVariable String token, Authentication authentication){

        String token1 = "";

        if(token == "DEV") {
            token1 = tokenService.generateDevToken(id, token);
        }else {
            token1 = tokenService.generateToken(id, token);
        }

        return token1;
    }

    @PostMapping("/admin/token/old")
    public String token(Authentication authentication){
        LOG.debug("Token request for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted {}", token);

        return token;
    }
}
