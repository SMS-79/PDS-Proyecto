package inf.pds.proy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import inf.pds.proy.adapters.jpa.repository.UsuarioJpaRepository;
import inf.pds.proy.adapters.mappers.TableroMapper;
import inf.pds.proy.adapters.mappers.UsuarioMapper;
import inf.pds.proy.domain.model.ListaTareas;
import inf.pds.proy.domain.model.Tablero;
import inf.pds.proy.domain.model.Usuario;
import inf.pds.proy.domain.model.ids.ListaTareasId;
import inf.pds.proy.domain.model.ids.TableroId;
import inf.pds.proy.domain.model.ids.UsuarioId;
import inf.pds.proy.adapters.jpa.repository.TableroJpaRepository;
import inf.pds.proy.adapters.jpa.entity.UsuarioEntity;
import inf.pds.proy.adapters.jpa.entity.TableroEntity;
import inf.pds.proy.adapters.jpa.entity.ListaTareasEntity;

import java.util.ArrayList;

@Component
public class InicializadorDatosPrueba implements CommandLineRunner {

    private final UsuarioMapper usuarioMapper;

    private final TableroMapper tableroMapper;

    private final UsuarioJpaRepository usuarioRepo;
    private final TableroJpaRepository tableroRepo;

    public InicializadorDatosPrueba(UsuarioJpaRepository usuarioRepo, TableroJpaRepository tableroRepo, TableroMapper tableroMapper, UsuarioMapper usuarioMapper) {
        this.usuarioRepo = usuarioRepo;
        this.tableroRepo = tableroRepo;
        this.tableroMapper = tableroMapper;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==================================================");
        System.out.println("--- INTENTANDO CREAR DATOS DE PRUEBA (AGREGADO JPA) ---");
        
        try {
            if (tableroRepo.count() == 0) {
                
                // 1. Guardamos el usuario raíz
                Usuario admin = new Usuario( UsuarioId.random() ,"pedro", "pedro@email.com", "1234");
                
                
                // 2. Preparamos el Tablero (Raíz del Agregado)
                Tablero tablero = new Tablero(TableroId.random(), "pedro", admin, "jamonero");
                
           
                // Inicializamos la lista de tareas dentro del agregado
              
                // 3. Preparamos las Listas de Tareas y las metemos al Tablero
                String[] nombresListas = {"TO DO", "DOING", "DONE"};
                
                for (String nombreLista : nombresListas) {
                    ListaTareas lista = new ListaTareas(ListaTareasId.random(), "Tarea");
                  
                    
                    // Al ser un agregado, simplemente la añadimos a la raíz
                    tablero.getListas().add(lista);
                }
                
                // 4. Guardamos todo el agregado de golpe (Cascada)
                admin = usuarioMapper.toDomain(usuarioRepo.save(usuarioMapper.toEntity(admin)));
                tablero = tableroMapper.toDomain(tableroRepo.save(tableroMapper.toEntity(tablero)));
                
                System.out.println("Tablero y listas guardadas con éxito. ID: " + tablero.getId());
                System.out.println("--- DATOS CREADOS CON ÉXITO ---");
            } else {
                System.out.println("--- EL TABLERO YA EXISTÍA ---");
            }
        } catch (Exception e) {
            System.err.println("!!! EXPLOSIÓN EN EL INICIALIZADOR !!!");
            e.printStackTrace(); 
        }
        System.out.println("==================================================");
    }
}