package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.websocket.Session;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author adalberto
 */
@Named
@ApplicationScoped
public class ManejadorSessionWS implements Serializable {
    final Set<Session> SESSIONES = new HashSet<>();
    
    public void agregar(Session s) {
        this.SESSIONES.add(s);
    }
    public void eliminar(Session s) {
        this.SESSIONES.remove(s);
    }

    public Set<Session> getSESSIONES() {
        return SESSIONES;
    }
}
