package inf.pds.proy.adapters.ui;

import inf.pds.proy.adapters.rest.UsuarioApiClient;

public class Configuracion {
    private static Configuracion instancia;
    private final UsuarioApiClient usuarioApiClient;

    public Configuracion(){
        usuarioApiClient = new UsuarioApiClient();
    }
    public static void setInstancia(Configuracion configuracion){
        instancia = configuracion;
    }

    public static Configuracion getInstancia(){
        return instancia;
    }

    public UsuarioApiClient getUsuarioApiClient(){
        return usuarioApiClient;
    }

}
