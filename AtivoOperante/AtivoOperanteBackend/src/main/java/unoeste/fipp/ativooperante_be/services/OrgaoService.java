package unoeste.fipp.ativooperante_be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.domain.entities.Orgao;
import unoeste.fipp.ativooperante_be.repositories.OrgaoRepository;

import java.util.List;

@Service
public class OrgaoService {
    @Autowired
    OrgaoRepository orgaoRepository;

    public List<Orgao> getAll(){
        return orgaoRepository.findAll();
    }

    public Orgao salvar(Orgao orgao){
        return orgaoRepository.save(orgao);
    }

    public boolean deletar(Orgao orgao){
        try{
            orgaoRepository.delete((orgao));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
