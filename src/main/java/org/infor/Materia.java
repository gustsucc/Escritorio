package org.infor;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Materia {
    private Long codigo;
    private StringProperty sigla;
    private StringProperty descrip;
    private List<Grupo> grupos;
    
    public Materia() {
        sigla = new SimpleStringProperty();
        descrip = new SimpleStringProperty();
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public Long getCodigo() {
        return codigo;
    }
    public StringProperty siglaProperty() {
        return sigla;
    }
    public String getSigla() {
        return sigla.get();
    }
    public void setSigla(String sigla) {
        this.sigla.set(sigla);
    }

    public StringProperty descripProperty() {
        return descrip;
    }
    public String getDescrip() {
        return descrip.get();
    }

    public void setDescrip(String descrip) {
        this.descrip.set(descrip);
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }
    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((descrip == null) ? 0 : descrip.hashCode());
        result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Materia other = (Materia) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        if (descrip == null) {
            if (other.descrip != null)
                return false;
        } else if (!descrip.equals(other.descrip))
            return false;
        if (sigla == null) {
            if (other.sigla != null)
                return false;
        } else if (!sigla.equals(other.sigla))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "{\"descrip\": \"" + getDescrip() + "\", \"sigla\": \"" + getSigla() + "\"}";
    }
    
    
}
