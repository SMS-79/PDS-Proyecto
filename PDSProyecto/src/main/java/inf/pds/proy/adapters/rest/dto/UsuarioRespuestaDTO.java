package inf.pds.proy.adapters.rest.dto;

import inf.pds.proy.domain.model.ids.UsuarioId;

public class UsuarioRespuestaDTO {
    
    private final UsuarioId usuarioId;
    private final String nombre;
    private final String email;

    public UsuarioRespuestaDTO(UsuarioId usuarioId, String nombre, String email) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.email = email;
    }

    public UsuarioId getUsuarioId() {
        return usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    
}
