package org.infor;
/*
Put header here


 */

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest.BodyPublishers;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ResourceBundle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLControllerGrupo implements Initializable {
    ObservableList<Materia> Materias = FXCollections.observableArrayList();
    
    @FXML
    private TextField new_grupo;
    @FXML
    private TableView<Grupo> tabla;
    @FXML
    private TableColumn<Grupo,String> ide;
    @FXML
    private Spinner<Integer> gest;
    @FXML
    private ComboBox<String> mat;
    
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) gest.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/primary.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
        }
    @FXML
    private void refrescar(ActionEvent event) throws ParseException {
        tabla.setItems(getData());
    }
    @FXML
    private void eliminar(ActionEvent event) throws org.json.simple.parser.ParseException, ParseException {
        Grupo sel = tabla.getSelectionModel().getSelectedItem();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/grupos/"+sel.getId().toString()))
        .timeout(Duration.ofMinutes(2)).header("Content-Type", "application/json")
        .DELETE().build();
        String Respuesta =client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body).join();
        if(Respuesta.isEmpty()) System.out.println("Borrado");
        else System.out.println("Error"+Respuesta);
        refrescar(event);
    }
    @FXML
    private void agregar(ActionEvent event) throws IOException, org.json.simple.parser.ParseException, ParseException {
        //POST JSON
        Grupo X = new Grupo();X.setGestion(gest.getValue().toString());
        X.setIdentificador(new_grupo.getText());
        int index = mat.getSelectionModel().getSelectedIndex();
        X.setMat(Materias.get(index));
        System.out.println(X.toString());
        JSONObject requestJSON = (JSONObject) new JSONParser().parse(X.toString());
        System.out.println(requestJSON);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/grupos"))
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(requestJSON.toJSONString()))
          .build();
        client.sendAsync(req, BodyHandlers.ofString())
          .thenApply(HttpResponse::body).join();
        refrescar(event); 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ide.setCellValueFactory(cellData -> cellData.getValue().getIdentificadorProperty());
        Integer anno = LocalDate.now().getYear();
        Materias = getMaterias();gest.getValueFactory().setValue(anno);
        for (int j=0;j<Materias.size();j++) {
            mat.getItems().add(Materias.get(j).getSigla()+" "+Materias.get(j).getDescrip());
        }
    }
    
    private ObservableList<Materia> getMaterias() {
        ObservableList<Materia> Data = FXCollections.observableArrayList();
        HttpClient client = HttpClient.newHttpClient();   
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8080/materias"))
        .timeout(Duration.ofMinutes(2))
        .header("Content-Type", "application/json")
        .build();
        String Respuesta = client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body).join();
        try {
            JSONObject Datos = (JSONObject) new JSONParser().parse(Respuesta);
            Datos = (JSONObject) Datos.get("_embedded");
            JSONArray Lista = (JSONArray) Datos.get("materias");
            for (int i=0;i<Lista.size();i++) {
                JSONObject Item = (JSONObject) Lista.get(i);
                String cod = ((JSONObject) ((JSONObject) Item.get("_links")).get("self")).get("href").toString();
                Materia M = new Materia();
                M.setCodigo(Long.parseLong(cod.substring(cod.lastIndexOf("/")+1, cod.length())));
                M.setSigla(Item.get("sigla").toString());M.setDescrip(Item.get("descrip").toString());
                Data.add(M);
            }
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return Data;
    }

    private ObservableList<Grupo> getData() throws ParseException {
        ObservableList<Grupo> Data = FXCollections.observableArrayList();
        HttpClient client = HttpClient.newHttpClient();
        int index = mat.getSelectionModel().getSelectedIndex();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8080/materias/"+Materias.get(index).getCodigo()+"/grupos"))
        .timeout(Duration.ofMinutes(2))
        .header("Content-Type", "application/json")
        .build();
        String Respuesta = client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body).join();
        
        try {
            JSONObject Datos = (JSONObject) new JSONParser().parse(Respuesta);
            Datos = (JSONObject) Datos.get("_embedded");
            JSONArray Lista = (JSONArray) Datos.get("grupoes");
            for (int i=0;i<Lista.size();i++) {
                JSONObject Item = (JSONObject) Lista.get(i);
                String cod = ((JSONObject) ((JSONObject) Item.get("_links")).get("self")).get("href").toString();
                System.out.println(Item);System.out.println(cod);
                if(gest.getValue().toString().equals(Item.get("gestion").toString())) { 
                    Data.add( 
                    new Grupo(Long.parseLong(cod.substring(cod.lastIndexOf("/")+1, cod.length())),
                        Item.get("identificador").toString(),Item.get("gestion").toString())
                    );
                }
            }
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return Data;
    }
}
