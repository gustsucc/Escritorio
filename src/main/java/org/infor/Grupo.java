package org.infor;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Grupo {
    private Long Id;
    private final StringProperty Identificador;
    private final StringProperty Gestion;


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
    public StringProperty getIdentificador() {
        return Identificador;
    }

    public String getGestion() {
        return Gestion.get();
    }

    public void setGestion(String Gestion) {
        this.Gestion.set(Gestion);
    }
    public StringProperty getGestion() {
        return Gestion;
    }


    @Override
    public String toString() {
        return "Grupo [Gestion=" + Gestion + ", Id=" + Id + ", Identificador=" + Identificador + "]";
    }

   
}
