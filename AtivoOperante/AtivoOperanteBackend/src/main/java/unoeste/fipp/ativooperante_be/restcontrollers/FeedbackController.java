package unoeste.fipp.ativooperante_be.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.ativooperante_be.services.FeedBackService;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController {

    @Autowired
    private FeedBackService feedBackService;

    @GetMapping("all")
    public ResponseEntity<Object> getAll(){
        var feedbackList = feedBackService.getAll();
        if(feedbackList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(feedbackList);
    }

}
