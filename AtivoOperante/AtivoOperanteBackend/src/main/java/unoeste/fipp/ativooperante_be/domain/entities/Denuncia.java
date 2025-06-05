package unoeste.fipp.ativooperante_be.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "denuncia")
public class Denuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "den_id")
    private Long id;
    @Column(name = "den_titulo")
    private String titulo;
    @Column(name = "den_texto")
    private String texto;
    @Column(name = "den_urgencia")
    private int urgencia;
    //private Orgao orgao;
    @Column(name = "den_data")
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "tip_id",nullable = false)
    private Tipo tipo;
    @ManyToOne
    @JoinColumn(name = "usu_id",nullable = false)
    private Usuario usuario;
    @OneToOne(mappedBy = "denuncia")
    private FeedBack feedBack;

    public Denuncia() {
    }

    public Denuncia(Long id, String titulo, String texto, int urgencia, LocalDate data, Tipo tipo) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.urgencia = urgencia;
        this.data = data;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(int urgencia) {
        this.urgencia = urgencia;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public FeedBack getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(FeedBack feedBack) {
        this.feedBack = feedBack;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}