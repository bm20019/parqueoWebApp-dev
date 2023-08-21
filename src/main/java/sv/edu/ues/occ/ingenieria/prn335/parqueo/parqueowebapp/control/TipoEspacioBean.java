package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.entity.TipoEspacio;

@Stateless
@LocalBean
public class TipoEspacioBean {

    @PersistenceContext(unitName = "ParqueoPU")
    EntityManager em;

    /**
     * Almacena un registro en la base de datos
     * @param registro Entidad a guardar
     * @throws IllegalStateException Si ocurre un error en el repositorio
     * @throws IllegalArgumentException Si el parametro es nulo
     */
    public void create(TipoEspacio registro) throws IllegalArgumentException, IllegalStateException {
        if(registro == null)
            throw new IllegalArgumentException();
        try {
            em.persist(registro);
            return;
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        throw new IllegalStateException();
    }
}
