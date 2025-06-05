package unoeste.fipp.ativooperante_be.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.ativooperante_be.domain.dtos.denuncia.DenunciaDTO;
import unoeste.fipp.ativooperante_be.domain.entities.Denuncia;
import unoeste.fipp.ativooperante_be.domain.entities.Erro;
import unoeste.fipp.ativooperante_be.domain.entities.FeedBack;
import unoeste.fipp.ativooperante_be.domain.entities.Usuario;
import unoeste.fipp.ativooperante_be.repositories.UsuarioRepository;
import unoeste.fipp.ativooperante_be.services.DenunciaService;

import java.util.List;

@RestController
@RequestMapping("api/denuncia")
public class DenunciaRestController {

    @Autowired
    private DenunciaService denunciaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Lista todas as denúncias
    @GetMapping("/all")
    public ResponseEntity<Object> getAll() {
        List<Denuncia> denunciaList = denunciaService.getAll();
        if (!denunciaList.isEmpty())
            return ResponseEntity.ok(denunciaList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum tipo cadastrado"));
    }

    // Lista denúncias por usuário
    @GetMapping
    public ResponseEntity<Object> getDenunciaByUser(@RequestParam(value = "userId") Long userId) {
        List<DenunciaDTO> denunciaList = denunciaService.getDenunciasByUser(userId);
        if (denunciaList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(denunciaList);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Denuncia denuncia) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        System.out.println(email);
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        denuncia.setUsuario(usuario);

        Denuncia _denuncia = denunciaService.save(denuncia);
        if(_denuncia==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(_denuncia);
    }

    // Adiciona um feedback a uma denúncia
    @PostMapping("/add-feedback/{texto}")
    public ResponseEntity<Object> addFeedBack( @PathVariable String texto, @RequestBody Denuncia denuncia) {
        if (denunciaService.addFeedBack(new FeedBack(texto, denuncia)))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body("Não foi possível adicionar o feedback");
    }

    // Deleta uma denúncia
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Denuncia denuncia) {
        if (denunciaService.delete(denuncia)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}