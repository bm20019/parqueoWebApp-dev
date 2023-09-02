package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.entity.TipoReserva;

@Named
@ViewScoped
public class FrmTipoReserva implements Serializable {

    @Inject
    TipoReservaBean trBean;

    List<TipoReserva> listaRegistro;
    int PageSize = 10, idSelecionado = -1;
    TipoReserva registro = null;

    public FrmTipoReserva() {

    }

    @PostConstruct
    public void inicializar() {
        inicializarRegistros();
    }

    public void inicializarRegistros() {
        this.listaRegistro = trBean.findRange(0, this.PageSize);
    }

    public void btnNuevoHandler(ActionEvent ae) {
        this.registro = new TipoReserva();
    }

    public void btnGuardarHandler(ActionEvent ae) {
        this.trBean.create(registro);
        this.listaRegistro = trBean.findRange(0, this.PageSize);
        this.registro = null;
    }

    public void btnModificarHandler() {
        TipoReserva modify = this.trBean.modify(registro);
        if (modify != null) {
            this.listaRegistro = trBean.findRange(0, this.PageSize);
            this.registro = null;
        }
    }

    public void btnEliminarHandler() {
    }

    public void btnCancelarHandler() {
        this.registro = null;
        this.listaRegistro = trBean.findRange(0, this.PageSize);
    }

    public void btnSelecionarHandler(ActionEvent ae) {
        this.idSelecionado = ae.getComponent().getAttributes().get("seleccionado").toString() == null ? -1 : (Integer) ae.getComponent().getAttributes().get("seleccionado");
        if (this.idSelecionado != -1) {
            this.registro = trBean.findById(this.idSelecionado);
        }
    }

    public List<TipoReserva> getListaRegistro() {
        return listaRegistro;
    }

    public void setListaRegistro(List<TipoReserva> listaRegistros) {
        this.listaRegistro = listaRegistros;
    }

    public TipoReserva getRegistro() {
        return registro;
    }

    public void setRegistro(TipoReserva registro) {
        this.registro = registro;
    }
}
