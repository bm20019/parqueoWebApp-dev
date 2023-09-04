package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.jsf;

import jakarta.faces.event.ActionEvent;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.AbstractDataAccess;

public abstract class AbstractFrm<T> {
  
    int PageSize = 10;
    public abstract T getDato();
    public abstract AbstractDataAccess getBean();
    T registro = null;
    LazyDataModel<T> modelo;
    List<T> listaRegistro;
    
    public AbstractFrm(){
    }
    
    public void iniciarFrm(){
        this.listaRegistro = getBean().findRange(0, this.PageSize);
        this.modelo = new LazyDataModel<T>(){
            @Override
            public int count(Map<String, FilterMeta> map) {
                return  Math.toIntExact(getBean().count());
            }
            @Override
            public List<T> load(int i, int i1, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                return getBean().findRange(i, i1);
            }
        };
    }
    
    public void btnNuevoHandler(ActionEvent ae) {
        this.registro = getDato();
    }

    public void btnGuardarHandler(ActionEvent ae) {
        this.getBean().create(registro);
        this.listaRegistro = getBean().findRange(0, this.PageSize);
        this.registro = null;
    }

    public void btnModificarHandler() {
        T modify = (T) this.getBean().modify(registro);
        if (modify != null) {
            this.listaRegistro = getBean().findRange(0, this.PageSize);
            this.registro = null;
        }
    }

    public void btnCancelarHandler() {
        this.registro = null;
        this.listaRegistro = getBean().findRange(0, this.PageSize);
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
    
    public List<T> getListaRegistro() {
        return listaRegistro;
    }

    public void setListaRegistro(List<T> listaRegistro) {
        this.listaRegistro = listaRegistro;
    }
}