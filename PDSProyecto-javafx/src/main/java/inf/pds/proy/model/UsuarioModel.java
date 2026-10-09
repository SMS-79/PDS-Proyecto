package inf.pds.proy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioModel {

    @JsonProperty ("id")
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
