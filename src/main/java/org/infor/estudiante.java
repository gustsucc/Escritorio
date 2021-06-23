package org.infor;

import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class estudiante {
    private final LongProperty RU;
    private final StringProperty Nombre;
    private final StringProperty Apellido;
	private final StringProperty email;
    private String foto;

    public LongProperty RUProperty() {
        return RU;
    }
    public Long getRU() {
        return RU.get();
    }
    public void setRU(Long identificador) {
        RU.set(identificador);
    }
    public StringProperty ApellidoProperty() {
        return Apellido;
    }
    public String getApellido() {
        return Apellido.get();
    }
    public void setApellido(String LastName) {
		this.Apellido.set(LastName);
	}
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public estudiante() {
        Nombre = new SimpleStringProperty("");
        Apellido = new SimpleStringProperty("");
        RU = new SimpleLongProperty();
        email = new SimpleStringProperty("");
        foto = "";
    }
    
    public String getName() {
        return Nombre.get();
    }
    public void setName(String firstName) {
		this.Nombre.set(firstName);
	}
    public StringProperty NameProperty() {
		return Nombre;
	}
    public String getCorreo() {
        return email.get();
    }
    public void setCorreo(String email) {
		this.email.set(email);
	}
    public StringProperty CorreoProperty() {
		return email;
	}
    

    @Override
    public String toString() {
        return "{\"nombre\":\"" + getName() + "\", \"apellido\":\""+getApellido()+"\",\"nacimiento\":\"" + getBirthday()
        + "\", \"email\":\"" + getCorreo()+ "\", \"id\":" + id + ", \"foto\":\""+getFoto()+ "\"}";
    }
    
}

