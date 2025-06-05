package unoeste.fipp.ativooperante_be.domain.dtos.denuncia;

public class FeedBackDto {
    public String texto;
    public Long denuncia;

    public FeedBackDto() {
    }

    public FeedBackDto(String texto, Long denuncia) {
        this.texto = texto;
        this.denuncia = denuncia;
    }

    public String getTexto() {
        return texto;
    }

    public Long getDenuncia() {
        return denuncia;
    }
}
