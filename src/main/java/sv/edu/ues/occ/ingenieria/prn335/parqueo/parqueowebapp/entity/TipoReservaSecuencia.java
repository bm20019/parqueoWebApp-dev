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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_reserva_secuencia", catalog = "parqueo", schema = "public")
@NamedQueries({
    @NamedQuery(name = "TipoReservaSecuencia.findAll", query = "SELECT t FROM TipoReservaSecuencia t"),
    @NamedQuery(name = "TipoReservaSecuencia.findByIdTipoReservaSecuencia", query = "SELECT t FROM TipoReservaSecuencia t WHERE t.idTipoReservaSecuencia = :idTipoReservaSecuencia"),
    @NamedQuery(name = "TipoReservaSecuencia.findByIndicaFin", query = "SELECT t FROM TipoReservaSecuencia t WHERE t.indicaFin = :indicaFin"),
    @NamedQuery(name = "TipoReservaSecuencia.findByNombre", query = "SELECT t FROM TipoReservaSecuencia t WHERE t.nombre = :nombre")})
public class TipoReservaSecuencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_reserva_secuencia")
    private Long idTipoReservaSecuencia;
    @Column(name = "indica_fin")
    private Boolean indicaFin;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idTipoReservaSecuencia", fetch = FetchType.LAZY)
    private List<ReservaHistorial> reservaHistorialList;
    @JoinColumn(name = "id_tipo_reserva", referencedColumnName = "id_tipo_reserva")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoReserva idTipoReserva;
    @OneToMany(mappedBy = "idTipoReservaSecuenciaPadre", fetch = FetchType.LAZY)
    private List<TipoReservaSecuencia> tipoReservaSecuenciaList;
    @JoinColumn(name = "id_tipo_reserva_secuencia_padre", referencedColumnName = "id_tipo_reserva_secuencia")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoReservaSecuencia idTipoReservaSecuenciaPadre;

    public TipoReservaSecuencia() {
    }

    public TipoReservaSecuencia(Long idTipoReservaSecuencia) {
        this.idTipoReservaSecuencia = idTipoReservaSecuencia;
    }

    public Long getIdTipoReservaSecuencia() {
        return idTipoReservaSecuencia;
    }

    public void setIdTipoReservaSecuencia(Long idTipoReservaSecuencia) {
        this.idTipoReservaSecuencia = idTipoReservaSecuencia;
    }

    public Boolean getIndicaFin() {
        return indicaFin;
    }

    public void setIndicaFin(Boolean indicaFin) {
        this.indicaFin = indicaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ReservaHistorial> getReservaHistorialList() {
        return reservaHistorialList;
    }

    public void setReservaHistorialList(List<ReservaHistorial> reservaHistorialList) {
        this.reservaHistorialList = reservaHistorialList;
    }

    public TipoReserva getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(TipoReserva idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public List<TipoReservaSecuencia> getTipoReservaSecuenciaList() {
        return tipoReservaSecuenciaList;
    }

    public void setTipoReservaSecuenciaList(List<TipoReservaSecuencia> tipoReservaSecuenciaList) {
        this.tipoReservaSecuenciaList = tipoReservaSecuenciaList;
    }

    public TipoReservaSecuencia getIdTipoReservaSecuenciaPadre() {
        return idTipoReservaSecuenciaPadre;
    }

    public void setIdTipoReservaSecuenciaPadre(TipoReservaSecuencia idTipoReservaSecuenciaPadre) {
        this.idTipoReservaSecuenciaPadre = idTipoReservaSecuenciaPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoReservaSecuencia != null ? idTipoReservaSecuencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoReservaSecuencia)) {
            return false;
        }
        TipoReservaSecuencia other = (TipoReservaSecuencia) object;
        if ((this.idTipoReservaSecuencia == null && other.idTipoReservaSecuencia != null) || (this.idTipoReservaSecuencia != null && !this.idTipoReservaSecuencia.equals(other.idTipoReservaSecuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335.parqueo.entityproyect.TipoReservaSecuencia[ idTipoReservaSecuencia=" + idTipoReservaSecuencia + " ]";
    }
    
}
