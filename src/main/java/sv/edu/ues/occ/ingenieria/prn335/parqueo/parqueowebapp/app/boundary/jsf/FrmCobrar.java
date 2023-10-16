package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.jsf;

import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.ws.NotificarEspacio;

/**
 *
 * @author adalberto
 */
@Named
@ViewScoped
public class FrmCobrar implements Serializable {
    @Inject
    NotificarEspacio ne;
    
    public void btnCobrarHandler(ActionEvent ae) {
        try {
            ne.propagarMensaje("Parque Libre");
        } catch (Exception e) {
            
        }
    }
}
