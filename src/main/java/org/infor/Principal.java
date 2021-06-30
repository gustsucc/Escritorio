package org.infor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Principal implements Initializable {
    @FXML
    private Label lblRef;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    @FXML
    private void Exit(ActionEvent event) {
        Platform.exit();
    }
    @FXML
    private void NewDocente(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) lblRef.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/agregarDocente.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
    }
    @FXML
    private void ListarDocente(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) lblRef.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Docente.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
    }
    @FXML 
    private void NewEstudiante(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) lblRef.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/agregarEstudiante.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
    }
    @FXML
    private void ListarEstudiante(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) lblRef.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/estudiante.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
    }
    @FXML
    private void ListarGrupos(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) lblRef.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/primaryGrupo.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
    }
    @FXML
    private void NewGrupo(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) lblRef.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/agregarGrupo.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
    }
    @FXML
    private void NewMateria(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) lblRef.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/agregarMateria.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
    }
    @FXML
    private void ListarMaterias(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) lblRef.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/primaryMateria.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
    }

}
