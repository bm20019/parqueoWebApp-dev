package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.entity;

import java.io.Serializable;
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
import jakarta.persistence.Table;

@Entity
@Table(name = "espacio_caracteristica", catalog = "parqueo", schema = "public")
@NamedQueries({
    @NamedQuery(name = "EspacioCaracteristica.findAll", query = "SELECT e FROM EspacioCaracteristica e")})
public class EspacioCaracteristica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_espacio_caracteristica")
    private Long idEspacioCaracteristica;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "valor")
    private String valor;
    @JoinColumn(name = "id_espacio", referencedColumnName = "id_espacio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Espacio idEspacio;
    @JoinColumn(name = "id_tipo_espacio", referencedColumnName = "id_tipo_espacio")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoEspacio idTipoEspacio;

    public EspacioCaracteristica() {
    }

    public EspacioCaracteristica(Long idEspacioCaracteristica) {
        this.idEspacioCaracteristica = idEspacioCaracteristica;
    }

    public Long getIdEspacioCaracteristica() {
        return idEspacioCaracteristica;
    }

    public void setIdEspacioCaracteristica(Long idEspacioCaracteristica) {
        this.idEspacioCaracteristica = idEspacioCaracteristica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Espacio getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(Espacio idEspacio) {
        this.idEspacio = idEspacio;
    }

    public TipoEspacio getIdTipoEspacio() {
        return idTipoEspacio;
    }

    public void setIdTipoEspacio(TipoEspacio idTipoEspacio) {
        this.idTipoEspacio = idTipoEspacio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspacioCaracteristica != null ? idEspacioCaracteristica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspacioCaracteristica)) {
            return false;
        }
        EspacioCaracteristica other = (EspacioCaracteristica) object;
        if ((this.idEspacioCaracteristica == null && other.idEspacioCaracteristica != null) || (this.idEspacioCaracteristica != null && !this.idEspacioCaracteristica.equals(other.idEspacioCaracteristica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335.parqueo.entityproyect.EspacioCaracteristica[ idEspacioCaracteristica=" + idEspacioCaracteristica + " ]";
    }
    
}
