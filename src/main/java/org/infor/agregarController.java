package org.infor;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ResourceBundle;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class agregarController implements Initializable {
    @FXML
    TextField input_Id;
    @FXML
    TextField input_Identificador;
    @FXML
    DatePicker input_Gestion;
    @FXML
    Button btnExit;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }
    @FXML
    private void btnClick(ActionEvent event) throws ParseException  {
        //POST JSON
        Grupo X = new Grupo(input_Id.getText(), input_Identificador.getText(), input_Gestion.getValue());
        
        JSONObject requestJSON = (JSONObject) new JSONParser().parse(X.toString());

        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/Grupo"))
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(requestJSON.toJSONString()))
          .build();
        client.sendAsync(req, BodyHandlers.ofString())
          .thenApply(HttpResponse::body).join();
        
    }
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) btnExit.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/primary.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
    }
}
