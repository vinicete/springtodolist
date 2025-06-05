package unoeste.fipp.ativooperante_be.restcontrollers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.ativooperante_be.domain.entities.Orgao;
import unoeste.fipp.ativooperante_be.repositories.OrgaoRepository;
import unoeste.fipp.ativooperante_be.services.OrgaoService;

import java.util.List;

@RestController
@RequestMapping("api/orgao")
public class OrgaoRestController {

    @Autowired
    OrgaoService orgaoService;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<Orgao> listOrgao = orgaoService.getAll();
        if(listOrgao.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(listOrgao);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Orgao orgao){
        Orgao orgaoAux = orgaoService.salvar(orgao);
        if(orgaoAux!=null){
            return ResponseEntity.ok(orgaoAux);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Orgao orgao){
        Orgao orgaoAux = orgaoService.salvar(orgao);
        if(orgaoAux!=null){
            return ResponseEntity.ok(orgaoAux);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Orgao orgao){
        if(orgaoService.deletar(orgao))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

}
