package org.infor;

import java.io.ObjectOutputStream.PutField;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class agregarController implements Initializable {
    @FXML
    TextField input_nombre;
    @FXML
    TextField input_email;
    @FXML
    DatePicker input_nac;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

    @FXML
    private void btnClick(ActionEvent event) throws ParseException  {
        //POST JSON
        Docente X = new Docente(148L,input_nombre.getText(), input_email.getText(), input_nac.getValue());
        System.out.println(X.toString());
        
        JSONObject requestJSON = (JSONObject) new JSONParser().parse(X.toString());
        /*requestJSON.put("id",150);
        requestJSON.put("nombre", X.getName());
        requestJSON.put("email",X.getCorreo());
        requestJSON.put("nacimiento",X.getBirthday().toString());
        System.out.println(requestJSON);*/

        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/docentes"))
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(requestJSON.toJSONString()))
          .build();
         client.sendAsync(req, BodyHandlers.ofString())
          .thenApply(HttpResponse::body).join();
        
    }
    
}
