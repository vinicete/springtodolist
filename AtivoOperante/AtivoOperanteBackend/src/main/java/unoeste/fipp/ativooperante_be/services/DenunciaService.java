package unoeste.fipp.ativooperante_be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.domain.dtos.denuncia.DenunciaDTO;
import unoeste.fipp.ativooperante_be.domain.dtos.denuncia.FeedBackDto;
import unoeste.fipp.ativooperante_be.domain.entities.Denuncia;
import unoeste.fipp.ativooperante_be.domain.entities.FeedBack;
import unoeste.fipp.ativooperante_be.repositories.DenunciaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DenunciaService {
    @Autowired
    private DenunciaRepository denunciaRepository;
    public List<Denuncia> getAll()
    {
        return denunciaRepository.findAll();
    }

    public Denuncia save(Denuncia denuncia){
        return denunciaRepository.save(denuncia);
    }

    public boolean addFeedBack(FeedBack feedBack){
        try {
            denunciaRepository.addFeedBack(feedBack.getDenuncia().getId(), feedBack.getTexto());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean delete(Denuncia denuncia){
        try{
            denunciaRepository.deleteById(denuncia.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<DenunciaDTO> getDenunciasByUser(Long id){
        List<Denuncia> denunciaList = denunciaRepository.getDenunciasByUsuarioId(id);
        List<DenunciaDTO> denunciaDtosList = new ArrayList<>();
        for(Denuncia d : denunciaList){
            FeedBackDto feedBack = null;

            if (d.getFeedBack() != null) {
                feedBack = new FeedBackDto(d.getFeedBack().getTexto(), d.getFeedBack().getDenuncia().getId());

                if(feedBack.getTexto() == null || feedBack.getTexto().isEmpty()) {
                    feedBack = null;
                }
            }

            DenunciaDTO denunciaDTO = new DenunciaDTO(d.getId(), d.getTitulo(), d.getTexto(), d.getUrgencia(), d.getData(), d.getTipo(), feedBack);
            denunciaDtosList.add(denunciaDTO);
        }
        return denunciaDtosList;
    }
}