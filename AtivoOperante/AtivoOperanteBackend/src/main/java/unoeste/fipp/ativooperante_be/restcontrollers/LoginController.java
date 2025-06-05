package unoeste.fipp.ativooperante_be.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.ativooperante_be.domain.dtos.login.SigninDto;
import unoeste.fipp.ativooperante_be.domain.dtos.login.SignupDto;
import unoeste.fipp.ativooperante_be.domain.dtos.login.TokenDto;
import unoeste.fipp.ativooperante_be.domain.entities.Erro;
import unoeste.fipp.ativooperante_be.services.LoginService;

@RestController
@RequestMapping("api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@RequestBody SignupDto req){

        try {
            var res = loginService.signup(req);
            return ResponseEntity.ok(res);
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(new Erro(ex.getMessage()));
        }
    }

    @PostMapping("signin")
    public ResponseEntity<Object> signin(@RequestBody SigninDto req){
        try {
            TokenDto token = loginService.signin(req);
            return ResponseEntity.ok(token);
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(new Erro(ex.getMessage()));
        }

    }
}
