package org.infor;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
// Primera sugerencia
public class Docente {
    private Long id;
    private final StringProperty Name;
	private final StringProperty correo;
	private final ObjectProperty<LocalDate> birthday;

    public Docente(String name, String correo, LocalDate birthday) {
        this.Name = new SimpleStringProperty(name);
        this.correo = new SimpleStringProperty(correo);
        this.birthday = new SimpleObjectProperty<LocalDate>(birthday);
    }

    public Docente(Long id, String name, String correo, LocalDate birthday) {
        this.id = id;
        this.Name = new SimpleStringProperty(name);
        this.correo = new SimpleStringProperty(correo);
        this.birthday = new SimpleObjectProperty<LocalDate>(birthday);
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
        return "{\"nombre\":\"" + getName() + "\", \"nacimiento\":\"" + getBirthday() + "\", \"email\":\"" + getCorreo() + "\", \"id\":" + id + "}";
    }
    
}
