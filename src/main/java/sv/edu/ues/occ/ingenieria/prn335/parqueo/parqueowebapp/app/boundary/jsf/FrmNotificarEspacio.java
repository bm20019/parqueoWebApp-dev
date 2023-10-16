package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.jsf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author adalberto
 */
@Named
@ViewScoped
public class FrmNotificarEspacio implements Serializable {
   public String getEspaciosLibres(){
     return String.valueOf(System.currentTimeMillis());
   } 
}
