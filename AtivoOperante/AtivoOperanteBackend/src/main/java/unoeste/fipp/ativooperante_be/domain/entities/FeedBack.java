package unoeste.fipp.ativooperante_be.domain.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "feedback")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id")
    private Long Id;
    
    @Column(name = "fee_texto")
    private String texto;
    
    @OneToOne
    @JoinColumn(name = "den_id", unique = true)
    @JsonIgnoreProperties("feedback")  // This breaks the circular reference
    private Denuncia denuncia;

    public FeedBack() {
    }

    public FeedBack(Long id, String texto, Denuncia denuncia) {
        Id = id;
        this.texto = texto;
        this.denuncia = denuncia;
    }

    public FeedBack(String texto, Denuncia denuncia) {
        this.texto = texto;
        this.denuncia = denuncia;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }
}