package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.entity.TipoEspacio;

public class TipoEspacioBeanTest {

    List<TipoEspacio> listaRegistro;

    public TipoEspacioBeanTest() {
        listaRegistro = new ArrayList();
    }

    /**
     * Test of create method, of class TipoEspacioBean.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        
        TipoEspacio registro = new TipoEspacio();
        registro.setNombre("registro 1");

        TipoEspacioBean cut = new TipoEspacioBean();

        assertThrows(IllegalArgumentException.class, () -> {
            cut.create(null);
        });
        
        
        assertThrows(IllegalStateException.class, () -> {
            cut.create(registro);
        });
        
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        cut.em = mockEM;
        cut.create(registro);
        
        //TipoEspacioBean mockBean = Mockito.mock(TipoEspacioBean.class);
        TipoEspacioBean espia = Mockito.spy(TipoEspacioBean.class);
        espia.em = mockEM;
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        
        try{
            espia.create(registro);
        }catch(Exception ex){
        }
        
        Mockito.verify(espia, Mockito.times(1)).getEntityManager();

        //fail("Test Create not pass");
    }

    @Test
    public void testFindRange() {
        System.out.println("findRange");
        int fist = 0;
        int pageSize = 10;

        TipoEspacioBean cut = new TipoEspacioBean();

        EntityManager em = Mockito.mock(EntityManager.class);
        TypedQuery tq = Mockito.mock(TypedQuery.class);
        Mockito.when(tq.getResultList()).thenReturn(listaRegistro);

        CriteriaBuilder mockCb = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery mockCq = Mockito.mock(CriteriaQuery.class);
        Mockito.when(mockCb.createQuery(TipoEspacio.class)).thenReturn(mockCq);

        Mockito.when(em.getCriteriaBuilder()).thenReturn(mockCb);
        Mockito.when(em.createQuery(mockCq)).thenReturn(tq);

        List<TipoEspacio> resultado = cut.findRange(-1, -1);
        assertTrue(resultado.isEmpty());
        cut.em = em;

        resultado = cut.findRange(fist, pageSize);
        
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        TipoEspacioBean espia = Mockito.spy(TipoEspacioBean.class);
        espia.em = mockEM;
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        
        try{
            espia.findRange(fist, pageSize);
        }catch(Exception ex){
        }
        
        Mockito.verify(espia, Mockito.times(1)).getEntityManager();
        // fail("Test FindRange not pass");
    }

    @Test
    public void testModify() {
        System.out.println("Modify Test");
        TipoEspacioBean cut = new TipoEspacioBean();
        TipoEspacio modificado = new TipoEspacio();

        modificado.setIdTipoEspacio(1);
        modificado.setNombre("Chepe");

        EntityManager mockEm = Mockito.mock(EntityManager.class);
        Mockito.when(mockEm.merge(modificado)).thenReturn(modificado);

        cut.em = mockEm;
        TipoEspacio resultado = cut.modify(null);
        assertNull(resultado);

        cut.em = mockEm;

        resultado = cut.modify(modificado);
        assertEquals(modificado, resultado);
        
        
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        TipoEspacioBean espia = Mockito.spy(TipoEspacioBean.class);
        espia.em = mockEM;
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        
        try{
            espia.modify(modificado);
        }catch(Exception ex){
        }
        Mockito.verify(espia, Mockito.times(1)).getEntityManager();
        
        //""""""""""""""
        EntityManager mockEM1 = Mockito.mock(EntityManager.class);
        TipoEspacioBean espia1 = Mockito.spy(new TipoEspacioBean());
        Mockito.doReturn(mockEM1).when(espia1).getEntityManager();
        Mockito.when(mockEM1.merge(Mockito.any())).thenThrow(IllegalStateException.class);
        espia1.em = mockEM1;
        try{
            espia1.modify(modificado);
        }catch(Exception ex){
        }
        
        Mockito.verify(mockEM1, Mockito.times(1)).merge(modificado);
        //fail("Test modify no pasado");
    }

    @Test
    public void testRemove() {
        System.out.println("Remove Test");
        TipoEspacioBean cut = new TipoEspacioBean();
        TipoEspacio modificado = new TipoEspacio();

        modificado.setIdTipoEspacio(1);
        modificado.setNombre("Chepe");

        EntityManager mockEm = Mockito.mock(EntityManager.class);

        assertThrows(IllegalArgumentException.class, () -> {
            cut.remove(null);
        });
        assertThrows(IllegalStateException.class, () -> {
            cut.remove(modificado);
        });

        cut.em = mockEm;
        cut.remove(modificado);

        Mockito.doThrow(IllegalStateException.class).when(mockEm).remove(Mockito.any());
        assertThrows(IllegalStateException.class, () -> {
            cut.remove(modificado);
        });

        
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        TipoEspacioBean espia = Mockito.spy(TipoEspacioBean.class);
        espia.em = mockEM;
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        
        try{
            espia.remove(modificado);
        }catch(Exception ex){
        }
        
        Mockito.verify(espia, Mockito.times(1)).getEntityManager();
        //fail("Test remove no pasado");
    }

    @Test
    public void testCount() {
        System.out.print("Contar");
        Long esperado = Long.valueOf(1);
        EntityManager mockEm = Mockito.mock(EntityManager.class);
        CriteriaBuilder mockCb = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery mockCq = Mockito.mock(CriteriaQuery.class);
        TypedQuery mockTq = Mockito.mock(TypedQuery.class);

        Mockito.when(mockEm.getCriteriaBuilder()).thenReturn(mockCb);
        Mockito.when(mockCb.createQuery(Long.class)).thenReturn(mockCq);
        Mockito.when(mockEm.createQuery(mockCq)).thenReturn(mockTq);
        Mockito.when(mockTq.getSingleResult()).thenReturn(esperado);

        TipoEspacioBean cut = new TipoEspacioBean();

        assertThrows(IllegalStateException.class, () -> {
            cut.count();
        });

        cut.em = mockEm;
        Long resultado = cut.count();
        assertNotNull(resultado);
        assertEquals(esperado, resultado);

        try {
            cut.em = null;
            cut.count();
            fail("Entity is Null");
        } catch (Exception ex) {
        }
        TipoEspacioBean spy = Mockito.spy(TipoEspacioBean.class);
        spy.em = mockEm;

        Mockito.when(spy.getEntityManager()).thenThrow(NullPointerException.class);

        try {
            spy.count();
        } catch (Exception ex) {
        }

        Mockito.verify(spy, Mockito.times(1)).getEntityManager();
        //fail("Test Count not pass");
    }
    
    @Test
    public void testFindById(){
        System.out.print("findById");
        Integer id = 1;
        EntityManager mockEm = Mockito.mock(EntityManager.class);
        
        TipoEspacioBean cut = new TipoEspacioBean();
        TipoEspacio esperado = new TipoEspacio();
        Mockito.when(mockEm.find(TipoEspacio.class, id)).thenReturn(esperado);
        assertThrows(IllegalArgumentException.class, ()-> {
            cut.findById(null);
        });
        assertThrows(IllegalStateException.class, ()-> {
            cut.findById(id);
        });
        
        cut.em=mockEm;
        TipoEspacio encontrado = cut.findById(id);
        assertNotNull(encontrado);
        assertEquals(esperado,encontrado);
        
        TipoEspacioBean espia = Mockito.spy(TipoEspacioBean.class);
        espia.em = mockEm;
        
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try{
            espia.findById(id);
        }catch(Exception ex){
            
        }
        Mockito.verify(espia, Mockito.times(1)).getEntityManager();
        
        //""""""""""""""
        EntityManager mockEM1 = Mockito.mock(EntityManager.class);
        TipoEspacioBean espia1 = Mockito.spy(new TipoEspacioBean());
        Mockito.doReturn(mockEM1).when(espia1).getEntityManager();
        Mockito.when(mockEM1.find(Mockito.any(),Mockito.any())).thenThrow(IllegalStateException.class);
        espia1.em = mockEM1;
        try{
            espia1.findById(id);
        }catch(Exception ex){
        }
        
        Mockito.verify(mockEM1, Mockito.times(1)).find(TipoEspacio.class, id);
        
        //fail("fail test findById");
    }
}
