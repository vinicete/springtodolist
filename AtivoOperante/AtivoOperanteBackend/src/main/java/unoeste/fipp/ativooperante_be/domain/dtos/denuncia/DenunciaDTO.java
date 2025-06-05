package unoeste.fipp.ativooperante_be.domain.dtos.denuncia;


import unoeste.fipp.ativooperante_be.domain.entities.FeedBack;
import unoeste.fipp.ativooperante_be.domain.entities.Tipo;

import java.time.LocalDate;
import java.util.Date;
public class DenunciaDTO {
    public Long id;
    public String titulo;
    public String texto;
    public int urgencia;
    public LocalDate data;
    public Tipo tipo;
    public FeedBackDto feedBack;

    public DenunciaDTO() {
    }

    public DenunciaDTO(Long id, String titulo, String texto, int urgencia, LocalDate data, Tipo tipo, FeedBackDto feedBack) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.urgencia = urgencia;
        this.data = data;
        this.tipo = tipo;
        this.feedBack = feedBack;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public LocalDate getData() {
        return data;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public FeedBackDto getFeedBack() {
        return feedBack;
    }
}
