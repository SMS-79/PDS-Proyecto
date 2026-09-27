package inf.pds.proy.domain.model.exceptions;

public class UsuarioNoExistenteException extends Exception{
    public UsuarioNoExistenteException(String m){
        super(m);
    }

    public UsuarioNoExistenteException(String m, Exception e){
        super(m, e);
    }
}
