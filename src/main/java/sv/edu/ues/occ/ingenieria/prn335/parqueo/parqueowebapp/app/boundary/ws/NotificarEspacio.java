package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.ws;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.ManejadorSessionWS;

/**
 *
 * @author adalberto
 */
@Named
@ApplicationScoped
@ServerEndpoint(value="/notificar_espacio")
public class NotificarEspacio implements Serializable{
    @Inject
    ManejadorSessionWS msWS;
    
    @OnOpen
    public void conecto(Session s) {
        msWS.agregar(s);
        System.out.println("Conecto");
    }
    
    @OnClose
    public void cerrar(Session s) {
        msWS.eliminar(s);
    }
    
    public void propagarMensaje(String mensaje) throws IOException {
        for (Session session: msWS.getSESSIONES()) {
            if (session.isOpen()) {
                session.getBasicRemote().sendText(mensaje);
            }
        }
    }
}
