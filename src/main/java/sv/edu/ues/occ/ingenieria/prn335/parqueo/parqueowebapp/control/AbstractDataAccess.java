package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDataAccess<T> {

    final Class tipoDato;

    public abstract EntityManager getEntityManager();

    public AbstractDataAccess(Class tipoDato) {
        this.tipoDato = tipoDato;
    }

    public void create(T registro) throws IllegalArgumentException, IllegalStateException {
        if (registro != null) {
            EntityManager em = null;
            try {
                em = getEntityManager();
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            
            if (em != null) {
                em.persist(registro);
                return;
            } else {
                throw new IllegalStateException();
            }
        }
        throw new IllegalArgumentException();
    }

    public T modify(T registro) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = null;
        try {
            em = getEntityManager();
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        if (registro != null) {
            if (em != null) {
                try {
                    return em.merge(registro);
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            throw new IllegalStateException();
        }
        return null;
    }

    public void remove(T registro) throws IllegalArgumentException, IllegalStateException {

        EntityManager em = null;
        try {
            em = getEntityManager();
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        if (registro != null) {
            if (em != null) {
                try {
                    em.remove(registro);
                    return;
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException();
    }

    public List<T> findRange(int fish, int pageSize) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = null;
        try {
            em = getEntityManager();
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        if (fish >= 0 && pageSize > 0) {
            if (em != null) {
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery cq = cb.createQuery(tipoDato);
                Root<T> raiz = cq.from(tipoDato);
                cq.select(raiz);
                TypedQuery q = em.createQuery(cq);
                q.setFirstResult(fish);
                q.setMaxResults(pageSize);
                return q.getResultList();
            }
        }
        return Collections.EMPTY_LIST;
    }

    public T findById(Object id) {
        EntityManager em = null;
        if (id != null) {
            try {
                em = getEntityManager();
            } catch (Exception ex) {
                //Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }

            if (em != null) {
                try {
                    return (T) em.find(tipoDato, id);
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException();
    }

    public Long count() throws IllegalStateException {
        EntityManager em = null;
        try {
            em = getEntityManager();
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        if (em != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            cq.select(cb.count(cq.from(tipoDato)));
            return em.createQuery(cq).getSingleResult();
        }
        throw new IllegalStateException();
    }
}
