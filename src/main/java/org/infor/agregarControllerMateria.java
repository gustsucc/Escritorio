package org.infor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class agregarControllerMateria {

    @FXML
    private Label titulo;

    @FXML
    private TextField sigla;

    @FXML
    private TextField descripcion;

    @FXML
    void cancelar(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) titulo.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/primary.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
    }

    @FXML
    void grabar(ActionEvent event) {
        // Creamos nuestra instancia de Materia con los datos de la TextField
        Materia M = new Materia();
        M.setSigla(sigla.getText().toString());
        M.setDescrip(descripcion.getText());
        
        // POST JSON
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/materias"))
            .header("Content-Type", "application/json")
            .POST(BodyPublishers.ofString(M.toString()))
            .build();
        client.sendAsync(req, BodyHandlers.ofString())
        .thenApply(HttpResponse::body).join();
    }
}