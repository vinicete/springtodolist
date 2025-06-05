package unoeste.fipp.ativooperante_be.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.ativooperante_be.domain.entities.Erro;
import unoeste.fipp.ativooperante_be.domain.entities.Tipo;
import unoeste.fipp.ativooperante_be.services.TipoService;

import java.util.List;

@RestController
@RequestMapping("api/tipo")
public class TipoRestController {
    @Autowired
    private TipoService tipoService;
    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<Tipo> tipoList;
        tipoList=tipoService.getAll();
        if (!tipoList.isEmpty())
            return ResponseEntity.ok(tipoList);
        else
            return ResponseEntity.badRequest().body(
                    new Erro("Nenhum tipo cadastrado"));
    }
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Tipo tipo){
        Tipo tipoAux=tipoService.salvar(tipo);
        if(tipoAux!=null)
            return ResponseEntity.ok(tipoAux);
        else
            return ResponseEntity.badRequest().body(
                    new Erro("Erro ao gravar o tipo"));
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Tipo tipo){
        Tipo tipoAux=tipoService.salvar(tipo);
        if(tipoAux!=null)
            return ResponseEntity.ok(tipoAux);
        else
            return ResponseEntity.badRequest().body(
                    new Erro("Erro ao atualizar o tipo"));
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Tipo tipo){
        if(tipoService.delete(tipo))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().body("Não foi possivel deletar o tipo");
    }
}
