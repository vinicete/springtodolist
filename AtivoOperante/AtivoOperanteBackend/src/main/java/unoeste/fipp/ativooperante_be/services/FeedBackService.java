package unoeste.fipp.ativooperante_be.services;

import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.domain.entities.FeedBack;
import unoeste.fipp.ativooperante_be.repositories.IFeedBackRepository;

import java.util.List;

@Service
public class FeedBackService {

    private IFeedBackRepository feedBackRepository;

    public FeedBackService(IFeedBackRepository feedBackRepository){
        this.feedBackRepository = feedBackRepository;
    }

    public List<FeedBack> getAll(){
        try{
            return feedBackRepository.findAll();
        }
        catch (Exception e){
            return null;
        }
    }
}
