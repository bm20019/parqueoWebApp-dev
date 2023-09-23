package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.jsf;

import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.AbstractDataAccess;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author home
 */
public abstract class AbstractFrm<T> implements Serializable {

    public abstract AbstractDataAccess getDataAccess();
    LazyDataModel<T> modelo;
    EstadosCRUD estado = EstadosCRUD.NINGUNO;
    T registro = null;

    public abstract FacesContext getFacesContext();

    @PostConstruct
    public void inicializar() {
        inicializarRegistros();
    }

    public void inicializarRegistros() {

        this.modelo = new LazyDataModel<T>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                int resultado = 0;
                AbstractDataAccess<T> trBean = null;
                try {
                    trBean = getDataAccess();
                    resultado = trBean.count();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
                return resultado;
            }

            @Override
            public List<T> load(int primero, int tamanio, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                List<T> resultado = null;
                try {

                    AbstractDataAccess<T> trBean = getDataAccess();

                    resultado = trBean.findRange(primero, tamanio);
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);

                } finally {
                    if (resultado == null) {
                        resultado = Collections.EMPTY_LIST;
                    }

                }
                return resultado;
            }

            @Override
            public String getRowKey(T object) {
                if (object != null) {
                    return getIdPorObjeto(object);
                }
                return null;
            }

            @Override
            public T getRowData(String rowKey) {
                if (rowKey != null) {
                    return getObjetoPorId(rowKey);
                }
                return null;
            }

        };

    }

    public abstract String getIdPorObjeto(T object);

    public abstract T getObjetoPorId(String id);
    
     public abstract void instanciarRegistro();
    
     public void seleccionarRegistro() {
        this.estado = EstadosCRUD.MODIFICAR;

    }
       public void btnNuevoHandler(ActionEvent ae) {
        this.instanciarRegistro();
        this.estado = EstadosCRUD.NUEVO;
}
         public void btnCancelarHandler(ActionEvent ae) {
        this.registro = null;
        this.estado = EstadosCRUD.NINGUNO;
    }
          public void btnModificarHandler(ActionEvent ae) {
        T modify = null;
        try {
           AbstractDataAccess<T> trBean = getDataAccess();
            modify = trBean.modify(registro);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        if (modify != null) {
            //TODO:notificar que se modifico
            this.estado = EstadosCRUD.NINGUNO;
            this.registro = null;
            return;

        }
        //TODO:notificar que no se cambio
    }
           public void btnEliminarHandler(ActionEvent ae) {
        try {
            AbstractDataAccess<T> trBean = getDataAccess();
        
            trBean.delete(registro);
            this.estado = EstadosCRUD.NINGUNO;
            this.registro=null;
            return;
            //TODO: enviar mensaje de exito
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        //TODO:notificar que no se elimino
    }
            public void btnGuardarHandler(ActionEvent ae) {
        FacesMessage mensaje=null;
        
        try {
             AbstractDataAccess<T> trBean = getDataAccess();
            trBean.create(registro);
            this.estado = EstadosCRUD.NINGUNO;
          mensaje=new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro guardado con exito", "Se creo el registrotro");
          getFacesContext().addMessage(null, mensaje);
          return;
          
          
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        mensaje=new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo guardar el registrotro", "no se creo el registrotro");
          getFacesContext().addMessage(null, mensaje);

        this.registro = null;

    }
 public T getRegis() {
        return registro;
    }

    public void setRegis(T registro) {
        this.registro = registro;
    }

    public LazyDataModel<T> getModelo() {
        return this.modelo;
    }

    public EstadosCRUD getEstado() {
        return estado;
    }

 }