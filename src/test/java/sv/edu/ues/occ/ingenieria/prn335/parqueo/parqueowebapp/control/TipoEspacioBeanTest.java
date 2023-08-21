package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.control;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.entity.TipoEspacio;
public class TipoEspacioBeanTest {
    
    public TipoEspacioBeanTest() {
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
        cut.em=mockEM;
        cut.create(registro);
        
        assertThrows(IllegalArgumentException.class, () -> {
            cut.create(null);
        });
        //fail("The test case is a prototype.");
    }
}
