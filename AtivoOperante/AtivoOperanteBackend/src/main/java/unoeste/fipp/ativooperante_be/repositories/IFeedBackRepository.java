package unoeste.fipp.ativooperante_be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.fipp.ativooperante_be.domain.entities.FeedBack;

public interface IFeedBackRepository extends JpaRepository<FeedBack, Long> {
}
