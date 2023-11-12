package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.entity.Area;
import java.io.Serializable;
import java.util.List;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.entity.Espacio;

@Stateless
@LocalBean
public class AreaBean extends AbstractDataAccess<Area> implements Serializable {
    
    @PersistenceContext(unitName="ParqueoPU")
    EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }
    
    public AreaBean(){
        super(Area.class);
    }
}
