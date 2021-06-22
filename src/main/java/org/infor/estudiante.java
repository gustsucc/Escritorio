package org.infor;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class estudiante {
    private Long id;
    private final StringProperty Nombre;
    private final StringProperty Apellido;
	private final StringProperty email;
	private final ObjectProperty<LocalDate> RU;
    private String foto;

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
        RU = new SimpleObjectProperty<LocalDate>();
        email = new SimpleStringProperty("");
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public LocalDate getBirthday() {
		return RU.get();
	}
    public ObjectProperty<LocalDate> birthdayProperty() {
        return RU;
    }
    public void setBirthday(LocalDate birthday) {
		this.RU.set(birthday);
	}

    @Override
    public String toString() {
        return "{\"nombre\":\"" + getName() + "\", \"apellido\":\""+getApellido()+"\",\"nacimiento\":\"" + getBirthday()
        + "\", \"email\":\"" + getCorreo()+ "\", \"id\":" + id + ", \"foto\":\""+getFoto()+ "\"}";
    }
    
}

