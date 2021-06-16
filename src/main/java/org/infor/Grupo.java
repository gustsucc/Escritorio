package org.infor;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Grupo {
    private final StringProperty Id;
    private final StringProperty Identificador;
    private final StringProperty Gestion;


    public Grupo(String id, String identificador, String gestion) {
        this.Id =new SimpleStringProperty(id);
        this.Identificador = new SimpleStringProperty(identificador);
        this.Gestion = new SimpleStringProperty(gestion);  
    }

    public StringProperty getId() {
        return Id;
    }

    public void setId(StringProperty id) {
        Id = id;
    }
    public StringProperty IdProperty() {
        return Id;
    }

    public StringProperty IdentificadorProperty() {
        return Identificador;
    }

    public void setIdentificador(String Identificador) {
        this.Identificador.set(Identificador);
    }

    public StringProperty getIdentificador() {
        return Identificador;
    }

    public void setGestion(String Gestion) {
        this.Gestion.set(Gestion);
    }

    public StringProperty getGestion() {
        return Gestion;
    }
    public StringProperty GestionProperty() {
        return Gestion;
    }

    @Override
    public String toString() {
        return "{\"Id\":\"" + getId() + "\", \"Identificador\":\"" + getIdentificador() + "\", \"Gestion\":\"" + getGestion()+"}";
    }
    
}
