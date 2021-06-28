package org.infor;


import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class agregarControllerGrupo implements Initializable {
    @FXML
    TextField input_id;
    @FXML
    TextField input_iden;
    @FXML
    Button btnExit;
    
    ObservableList<String> list=FXCollections.observableArrayList();
    @FXML
    ChoiceBox <String> item;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gestion();
    }

    @FXML
    private void btnClick(ActionEvent event) throws ParseException  {
        //POST JSON
        Grupo X = new Grupo(Long.parseLong(input_id.getText()), input_iden.getText(), item.getValue());  
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/primaryGrupo.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
    }

    @FXML
    private void gestion(){
        list.removeAll(list);
        String a="2015";
        String b="2016";
        String c="2017";
        String d="2018";
        String e="2019";
        String f="2020";
        String g="2021";
        String h="2022";
        String i="2023";list.addAll(a,b,c,d,e,f,g,h,i);
        item.setItems(list);
    }
}
