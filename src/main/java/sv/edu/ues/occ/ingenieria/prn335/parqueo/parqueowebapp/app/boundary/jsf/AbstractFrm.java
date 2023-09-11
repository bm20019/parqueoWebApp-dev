package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.AbstractDataAccess;

public abstract class AbstractFrm<T> implements Serializable{

    public abstract AbstractDataAccess<T> getDataAccess();

    public abstract String getIdPorObjeto(T object);

    public abstract T getObjetoPorId(String rowKey);

    public abstract FacesContext getFacesContext();

    public abstract void instanciarRegistro();

    LazyDataModel<T> modelo = null;
    EstadosCRUD estado = EstadosCRUD.NINGUNO;
    T registro = null;
    //Loggers
    Logger logger = null;
    FileHandler fileHandler = null;

    public AbstractFrm() {
    }

    @PostConstruct
    public void inicializar() {
        this.initRegistro();
    }
    
    public void initRegistro() {
        logger = Logger.getLogger(getClass().getName());
        try {
            fileHandler = new FileHandler("/home/adalberto/Documentos/LOG/LogAbstractFRM.txt");
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        this.modelo = new LazyDataModel<T>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                AbstractDataAccess<T> trBean = null;
                int resultado = 0;
                try {
                    trBean = getDataAccess();
                    resultado = trBean.count();
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, ex.getMessage(), ex);
                }
                return resultado;
            }

            @Override
            public List<T> load(int primero, int tamanio, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                AbstractDataAccess<T> trBean = null;
                List<T> resultado = null;
                try {
                    trBean = getDataAccess();
                    resultado = trBean.findRange(primero, tamanio);
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, ex.getMessage(), ex);

                } finally {
                    if (resultado == null) {
                        resultado = Collections.EMPTY_LIST;
                    }
                }
                return resultado;
            }

            @Override
            public String getRowKey(T object) {
                return getIdPorObjeto(object);
            }

            @Override
            public T getRowData(String id) {
                if (id != null) {
                    getObjetoPorId(id);
                }
                return null;
            }
        };
    }

    public void seleccionarRegistro() {
        this.estado = EstadosCRUD.MODIFICAR;
        System.err.println("EL ID DEL REGISTRO ES: "+registro.toString());
        logger.log(Level.INFO,registro.toString());
    }

    public void btnNuevoHandler(ActionEvent ae) {
        this.instanciarRegistro();
        this.estado = EstadosCRUD.NUEVO;
    }

    public void btnCancelarHandler() {
        this.registro = null;
        this.estado = EstadosCRUD.NINGUNO;
    }

    public void btnModificarHandler(ActionEvent ae) {
        T modify = null;
        try {
            AbstractDataAccess<T> trBean = getDataAccess();
            modify = trBean.modify(registro);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        if (modify != null) {
            this.estado = EstadosCRUD.NINGUNO;
            this.registro = null;
            return;
        }
    }

    public void btnEliminarHandler(ActionEvent ae) {
        try {
            AbstractDataAccess<T> trBean = getDataAccess();
            trBean.delete(registro);
            this.estado = EstadosCRUD.NINGUNO;
            this.registro = null;
            return;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void btnGuardarHandler(ActionEvent ae) {
        FacesMessage mensaje = null;
        try {
            AbstractDataAccess<T> trBean = getDataAccess();
            trBean.create(registro);
            this.estado = EstadosCRUD.NINGUNO;
            mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Guardado con exito", "Se creo el registro con exito");
            getFacesContext().addMessage(null, mensaje);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha podido guardar el registro", "No se pudo almacenar el registro");
        getFacesContext().addMessage(null, mensaje);
        this.registro = null;
    }

    public T getRegistro() {
        return registro;
    }

    public void setRegistro(T registro) {
        this.registro = registro;
    }

    public LazyDataModel<T> getModelo() {
        return modelo;
    }

    public EstadosCRUD getEstado() {
        return estado;
    }
}
