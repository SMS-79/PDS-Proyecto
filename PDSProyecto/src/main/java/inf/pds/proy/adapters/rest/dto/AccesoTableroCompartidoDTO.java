package inf.pds.proy.adapters.rest.dto;

public class AccesoTableroCompartidoDTO {

    public record SolicitudAcceso(String url, String email) {}

    public record RespuestaAcceso(Long tableroId, boolean yaEraMiembro) {}
}
