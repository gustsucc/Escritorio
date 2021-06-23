package org.infor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
        // TODO Auto-generated method stub
        
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
}
