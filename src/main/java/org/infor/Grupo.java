package org.infor;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Grupo {
    private Long Id;
    private final StringProperty Identificador;
    private final StringProperty Gestion;
    private Materia mat;
    
    public Materia getMat() {
        return mat;
    }

    public void setMat(Materia mat) {
        this.mat = mat;
    }

    public Grupo() {
        this.Identificador = new SimpleStringProperty();
        this.Gestion = new SimpleStringProperty(); 
    }

    public Grupo(Long Id, String Identificador, String Gestion) {
        this.Id = Id;
        this.Identificador = new SimpleStringProperty(Identificador);
        this.Gestion = new SimpleStringProperty(Gestion);  
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public void setIdentificador(String firstIdentificador) {
        this.Identificador.set(firstIdentificador);
    }

    public String getIdentificador() {
        return Identificador.get();
    }

    public StringProperty getIdentificadorProperty() {
        return Identificador;
    }

    public void setGestion(String Gestion) {
        this.Gestion.set(Gestion);
    }

    public String getGestion() {
        return Gestion.get();
    }
    public StringProperty GestionProperty() {
        return Gestion;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + getId() + "\", \"identificador\":\"" + getIdentificador() + 
        "\", \"mat\":\"http://localhost:8080/materias/"+getMat().getCodigo()+
        "\", \"gestion\":\"" + getGestion()+"\"}";
    }
    
}
