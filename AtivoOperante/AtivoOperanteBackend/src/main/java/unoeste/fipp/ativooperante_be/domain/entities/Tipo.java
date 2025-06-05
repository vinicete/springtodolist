package unoeste.fipp.ativooperante_be.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tipo")
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tip_id")
    private Long id;
    @Column(name="tip_nome")
    private String nome;
    public Tipo(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Tipo() {
        this(0L,"");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
