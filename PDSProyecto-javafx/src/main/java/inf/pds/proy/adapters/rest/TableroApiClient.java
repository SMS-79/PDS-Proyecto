package inf.pds.proy.adapters.rest;

import inf.pds.proy.model.TableroModel;
import java.util.List;

public class TableroApiClient {

    public List<TableroModel> obtenerTableros() {
        // GET http://localhost:8080/api/tableros
        // JSON → List<TableroModel>
    }

    public void crearTarjetaTarea(Long tableroId, Long listaId, String nombre) {
        // POST /api/tableros/{tableroId}/listas/{listaId}/tarjeta
    }
}
