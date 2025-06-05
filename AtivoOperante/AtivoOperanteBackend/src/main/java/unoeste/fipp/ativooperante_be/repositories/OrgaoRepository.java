package unoeste.fipp.ativooperante_be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.fipp.ativooperante_be.domain.entities.Orgao;

public interface OrgaoRepository extends JpaRepository<Orgao, Long> {
}
