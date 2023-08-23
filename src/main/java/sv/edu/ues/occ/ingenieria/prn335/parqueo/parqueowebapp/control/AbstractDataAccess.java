package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDataAccess<T> {

    public abstract EntityManager getEntityManager();

    /**
     * Almacena un registro en la base de datos
     *
     * @param registro Entidad a guardar
     * @throws IllegalStateException Si ocurre un error en el repositorio
     * @throws IllegalArgumentException Si el parametro es nulo
     */
    public void create(T registro) throws IllegalArgumentException, IllegalStateException {
        if (registro == null) {
            throw new IllegalArgumentException();
        }
        try {
            getEntityManager().persist(registro);
            return;
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        throw new IllegalStateException();
    }

    public List<T> findRange(int fish, int pageSize) throws IllegalArgumentException, IllegalStateException {
        if (fish < 0 || pageSize <= 0) {
            throw new IllegalArgumentException();
        }
        Query q;
        try {
            q = getEntityManager().createNamedQuery("TipoEspacio.findAll");
            q.setFirstResult(fish);
            q.setMaxResults(pageSize);
        } catch (Exception ex) {
            throw new IllegalStateException();
        }
        return q.getResultList();
    }

    public T modify(T registro) throws IllegalArgumentException, IllegalStateException {
        if (registro == null) {
            throw new IllegalArgumentException();
        }
        if (getEntityManager() == null) {
            throw new NullPointerException();
        }

        try {
            return getEntityManager().merge(registro);
        } catch (Exception ex) {
            throw new IllegalStateException();
        }
    }
}
