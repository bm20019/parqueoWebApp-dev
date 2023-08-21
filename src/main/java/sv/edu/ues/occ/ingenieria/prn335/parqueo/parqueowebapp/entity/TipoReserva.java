package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_reserva", catalog = "parqueo", schema = "public")
@NamedQueries({
    @NamedQuery(name = "TipoReserva.findAll", query = "SELECT t FROM TipoReserva t"),
    @NamedQuery(name = "TipoReserva.findByIdTipoReserva", query = "SELECT t FROM TipoReserva t WHERE t.idTipoReserva = :idTipoReserva"),
    @NamedQuery(name = "TipoReserva.findByNombre", query = "SELECT t FROM TipoReserva t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoReserva.findByPublico", query = "SELECT t FROM TipoReserva t WHERE t.publico = :publico"),
    @NamedQuery(name = "TipoReserva.findByDescripcion", query = "SELECT t FROM TipoReserva t WHERE t.descripcion = :descripcion")})
public class TipoReserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_reserva")
    private Integer idTipoReserva;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "publico")
    private Boolean publico;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idTipoReserva", fetch = FetchType.LAZY)
    private List<TipoReservaSecuencia> tipoReservaSecuenciaList;
    @OneToMany(mappedBy = "idTipoReserva", fetch = FetchType.LAZY)
    private List<Reserva> reservaList;

    public TipoReserva() {
    }

    public TipoReserva(Integer idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public Integer getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(Integer idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getPublico() {
        return publico;
    }

    public void setPublico(Boolean publico) {
        this.publico = publico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<TipoReservaSecuencia> getTipoReservaSecuenciaList() {
        return tipoReservaSecuenciaList;
    }

    public void setTipoReservaSecuenciaList(List<TipoReservaSecuencia> tipoReservaSecuenciaList) {
        this.tipoReservaSecuenciaList = tipoReservaSecuenciaList;
    }

    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoReserva != null ? idTipoReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoReserva)) {
            return false;
        }
        TipoReserva other = (TipoReserva) object;
        if ((this.idTipoReserva == null && other.idTipoReserva != null) || (this.idTipoReserva != null && !this.idTipoReserva.equals(other.idTipoReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335.parqueo.entityproyect.TipoReserva[ idTipoReserva=" + idTipoReserva + " ]";
    }
    
}
