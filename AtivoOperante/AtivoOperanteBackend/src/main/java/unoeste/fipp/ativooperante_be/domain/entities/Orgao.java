package unoeste.fipp.ativooperante_be.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orgaos")
public class Orgao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private int id;
    @Column(name = "org_nome")
    private String nome;

    public Orgao(){}

    public Orgao(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
