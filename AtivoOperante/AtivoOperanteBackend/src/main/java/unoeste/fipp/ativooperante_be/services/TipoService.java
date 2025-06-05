package unoeste.fipp.ativooperante_be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.domain.entities.Tipo;
import unoeste.fipp.ativooperante_be.repositories.TipoRepository;

import java.util.List;

@Service
public class TipoService {
    @Autowired
    private TipoRepository tipoRepository;
    public List<Tipo> getAll()
    {
        return tipoRepository.findAll();
    }
    public Tipo salvar(Tipo tipo){
        return tipoRepository.save(tipo);
    }
    public boolean delete(Tipo tipo){
        try{
            tipoRepository.deleteById(tipo.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
