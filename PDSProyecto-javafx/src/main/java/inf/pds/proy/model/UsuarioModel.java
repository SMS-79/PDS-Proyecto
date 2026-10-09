package inf.pds.proy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioModel {

    public IdentificadorModel usuarioId;

    public UsuarioModel() {
    }

    public IdentificadorModel getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(IdentificadorModel usuarioId) {
        this.usuarioId = usuarioId;
    }

    

}
