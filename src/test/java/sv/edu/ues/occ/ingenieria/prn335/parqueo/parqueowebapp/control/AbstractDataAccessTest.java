package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.entity.TipoEspacio;

public class AbstractDataAccessTest {

    List<TipoEspacio> listaRegistro;

    public AbstractDataAccessTest() {
        listaRegistro = new ArrayList();
        listaRegistro.add(new TipoEspacio(1));
        listaRegistro.add(new TipoEspacio(2));
    }

    /**
     * Test of create method, of class TipoEspacioBean.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        TipoEspacio registro = new TipoEspacio();
        registro.setNombre("registro 1");

        EntityManager mockEM = Mockito.mock(EntityManager.class);
        TipoEspacioBean cut = new TipoEspacioBean();
        assertThrows(Exception.class, () -> {
            cut.create(registro);
        });
        cut.em = mockEM;
        cut.create(registro);
        assertThrows(IllegalArgumentException.class, () -> {
            cut.create(null);
        });
    }

    @Test
    public void testfindAll() {
        System.out.println("FIND ALL");

        TipoEspacioBean cut = new TipoEspacioBean();
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        Query mockQ = Mockito.mock(Query.class);

        //Primera validacion
        assertThrows(IllegalArgumentException.class, () -> {
            cut.findRange(-1, 0);
        });

        //Segunda
        Mockito.when(mockEM.createNamedQuery("")).thenReturn(mockQ);
        cut.em = mockEM;
        assertThrows(IllegalStateException.class, () -> {
            cut.findRange(0, 10);
        });

        //Tercera
        Mockito.when(mockEM.createNamedQuery("TipoEspacio.findAll")).thenReturn(mockQ);
        Mockito.when(mockQ.getResultList()).thenReturn(listaRegistro);
        assertEquals(2, cut.findRange(0, 2).size());
    }

    @Test
    public void testmodify() {
        System.out.println("Modify Test");
        TipoEspacioBean cut = new TipoEspacioBean();
        //
        assertThrows(IllegalArgumentException.class, () -> {
            cut.modify(null);
        });
        TipoEspacio registro = listaRegistro.get(0);
        //
        cut.em = null;
        assertThrows(NullPointerException.class, () -> {
            cut.modify(registro);
        });
        //
        EntityManager mockEm = Mockito.mock(EntityManager.class);
        cut.em = mockEm;
        Mockito.when(cut.modify(registro)).thenThrow(new IllegalStateException());
        assertThrows(IllegalStateException.class, () -> {
            cut.modify(registro);
        });
    }

    public class AbstractDataAccessImpl extends AbstractDataAccess {

        public EntityManager getEntityManager() {
            return null;
        }
    }

}
