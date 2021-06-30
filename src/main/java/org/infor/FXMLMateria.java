package org.infor;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.ParseException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FXMLMateria implements Initializable {

    @FXML
    private TableView<Materia> tabla;

    @FXML
    private TableColumn<Materia, String> sigla;

    @FXML
    private TableColumn<Materia, String> descripcion;

    @FXML
    private Button btnElim;

    @FXML
    private Button agreg;

    @FXML
    private Button Back;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sigla.setCellValueFactory(cellData -> cellData.getValue().siglaProperty());
        descripcion.setCellValueFactory(cellData -> cellData.getValue().descripProperty());
        // Obtenemos los datos a ser mostrados en nuestro Grilla Visual
        try {
            tabla.setItems(getData());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void agregar(ActionEvent event) {

    }

    @FXML
    void regresar(ActionEvent event) {

    }
    @FXML
    void editar(ActionEvent event) {

    }
    @FXML
    void eliminar(ActionEvent event) {

    }
    private ObservableList<Materia> getData() throws ParseException {
        // Creamos una Lista de Docentes a ser devuelta por el método
        ObservableList<Materia> Data = FXCollections.observableArrayList();
        // Iniciamos nuestro Cliente HTTP para Microservicio REST
        HttpClient client = HttpClient.newHttpClient();
        // Configuramos nuestro requerimiento, estipulando trabajar con JSON
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8080/materias"))
        .timeout(Duration.ofMinutes(2))
        .header("Content-Type", "application/json")
        .build();
        // Obtenemos la respuesta de manera Asíncrona
        String Respuesta = client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body).join();
        // Procesamos nuestro Objeto JSON que incluye varios elementos
        try { // Obtenemos el objeto JSON a partir de la cadena Respuesta 
            JSONObject Datos = (JSONObject) new JSONParser().parse(Respuesta);
            // Obtenemos el objeto JSON correspondiente a Embedded (Embebido/Datos)
            Datos = (JSONObject) Datos.get("_embedded");
            // Obtenemos el Array JSON correspondiente a Docentes (Listado)
            JSONArray Lista = (JSONArray) Datos.get("materias");
            for (int i=0;i<Lista.size();i++) {
                // Obtenemos cada uno de los elementos contenidos en el Array
                JSONObject Item = (JSONObject) Lista.get(i);
                // Obtenemos el ID del Objeto que se presenta en "_links.self.href"
                String cod = ((JSONObject) ((JSONObject) Item.get("_links")).get("self")).get("href").toString();
                Materia dato = new Materia(); //Creo que Objeto Docente para setear sus atributos
                dato.setCodigo(Long.parseLong(cod.substring(cod.lastIndexOf("/")+1, cod.length())));
                dato.setSigla(Item.get("sigla").toString());
                dato.setDescrip(Item.get("descrip").toString());
                // Añadimos el elemento a nuestra lista que será devuelta por el método
                Data.add(dato);
            }
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return Data;
    }
}

