package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.entity.Area;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.AbstractDataAccess;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.AreaBean;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.entity.Espacio;

@Named
@ViewScoped
public class FrmArea extends AbstractFrm<Area> implements Serializable {

    @Inject
    AreaBean arBean;
    @Inject
    FacesContext fc;

    @Override
    public AbstractDataAccess getDataAccess() {
        return this.arBean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdPorObjeto(Area object) {
        if (object != null && object.getIdArea() != null) {
            return object.getIdArea().toString();
        }
        return null;
    }
    
    public List<Espacio> getEspacios(String id){
        Area are = this.getObjetoPorId(id);
        return are.getEspacioList();
    }

    @Override
    public Area getObjetoPorId(String id) {
        if (id != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdArea().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new Area();
    }
}
