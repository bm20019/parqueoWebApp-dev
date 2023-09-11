package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.stream.Collectors;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.AbstractDataAccess;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.entity.TipoReserva;

@Named
@ViewScoped
public class FrmTipoReserva extends AbstractFrm<TipoReserva> implements Serializable {

    @Inject
    TipoReservaBean trBean;
    
    @Inject
    FacesContext fc;

    public FrmTipoReserva() {

    }

    @Override
    public AbstractDataAccess<TipoReserva> getDataAccess() {
        return this.trBean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdPorObjeto(TipoReserva object) {
        if (object != null && object.getIdTipoReserva() != null) {
            return object.getIdTipoReserva().toString();
        }
        return null;
    }

    @Override
    public TipoReserva getObjetoPorId(String rowKey) {
        if (rowKey != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdTipoReserva().toString().equals(rowKey)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new TipoReserva();
    }
}
