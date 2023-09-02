package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.entity.TipoEspacio;

@Stateless
@LocalBean
public class TipoEspacioBean extends AbstractDataAccess<TipoEspacio> implements Serializable {

    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    public TipoEspacioBean(){
        super(TipoEspacio.class);
    }
}
