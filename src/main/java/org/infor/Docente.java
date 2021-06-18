package org.infor;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Docente {
    private Long id;
    private final StringProperty Name;
    private final StringProperty Apellido;
	private final StringProperty correo;
	private final ObjectProperty<LocalDate> birthday;
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

    public Docente() {
        Name = new SimpleStringProperty("");
        Apellido = new SimpleStringProperty("");
        birthday = new SimpleObjectProperty<LocalDate>();
        correo = new SimpleStringProperty("");
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return Name.get();
    }
    public void setName(String firstName) {
		this.Name.set(firstName);
	}
    public StringProperty NameProperty() {
		return Name;
	}
    public String getCorreo() {
        return correo.get();
    }
    public void setCorreo(String email) {
		this.correo.set(email);
	}
    public StringProperty CorreoProperty() {
		return correo;
	}
    public LocalDate getBirthday() {
		return birthday.get();
	}
    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
	}

    @Override
    public String toString() {
        return "{\"nombre\":\"" + getName() + "\", \"apellido\":\""+getApellido()+"\",\"nacimiento\":\"" + getBirthday()
        + "\", \"email\":\"" + getCorreo()+ "\", \"id\":" + id + ", \"foto\":\""+getFoto()+ "\"}";
    }
    
}
