package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.AbstractDataAccess;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.entity.TipoReserva;

@Named
@ViewScoped
public class FrmTipoReserva extends AbstractFrm<TipoReserva> implements Serializable {

    @Inject
    TipoReservaBean trBean;

    int idSelecionado = -1;
    TipoReserva registro = new TipoReserva();
    public FrmTipoReserva() {

    }

    @PostConstruct
    public void inicializar() {
        this.iniciarFrm();
    }

    public void btnSelecionarHandler(ActionEvent ae) {
        this.idSelecionado = ae.getComponent().getAttributes().get("seleccionado").toString() == null ? -1 : (Integer) ae.getComponent().getAttributes().get("seleccionado");
        if (this.idSelecionado != -1) {
            this.registro = trBean.findById(this.idSelecionado);
        }
    }

    @Override
    public AbstractDataAccess getBean() {
        return this.trBean;
    }

    @Override
    public TipoReserva getDato() {
        return this.registro;
    }
}