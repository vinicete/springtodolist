package unoeste.fipp.ativooperante_be.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unoeste.fipp.ativooperante_be.domain.entities.Denuncia;

import java.util.List;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia,Long> {
   //findByUsuario
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO feedback (fee_texto, den_id) VALUES (:fee_texto, :den_id)",nativeQuery = true)
    public void addFeedBack(@Param("den_id") Long id, @Param("fee_texto") String texto );
    
    public List<Denuncia> getDenunciasByUsuarioId(Long id);
}
