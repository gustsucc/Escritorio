package org.infor;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
    private final StringProperty login;
    private final StringProperty clave;

    public Usuario(String login, String clave) {
        this.login = new SimpleStringProperty(login);
        this.clave = new SimpleStringProperty(clave);
    }

    public String getLogin() {
        return login.get();
    }

    public void setLogin(String firstLogin) {
        this.login.set(firstLogin);
    }
   
    public String getClave() {
        return clave.get();
    }

    public void setClave(String firstClave) {
        this.clave.set(firstClave);
    }

    public StringProperty loginProperty() {
		return login;
	}
    public StringProperty claveProperty() {
		return clave;
	}

    @Override
    public String toString() {
        return "Usuario [clave=" + clave + ", login=" + login + "]";
    }
    
    
}
