package org.infor;
/*
Put header here


 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Base64;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLDocente implements Initializable {

    @FXML
    private Label lblOut;
    @FXML
    private TableView<Docente> tabla;
    @FXML
    private TableColumn<Docente,String> nombre;
    @FXML
    private TableColumn<Docente,String> correo;
    @FXML
    private TableColumn<Docente,LocalDate> fecha;
    @FXML
    private TableColumn<Docente,String> apellidos;
    @FXML
    private ImageView foto;

    @FXML
    private void eliminar(ActionEvent event) throws org.json.simple.parser.ParseException {
        Docente sel = tabla.getSelectionModel().getSelectedItem();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/docentes/"+sel.getId().toString()))
        .timeout(Duration.ofMinutes(2)).header("Content-Type", "application/json")
        .DELETE().build();

        String Respuesta =client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body).join();

        if(Respuesta.isEmpty()) System.out.println("Borrado");
        else System.out.println("Hubo un error");
    }
    @FXML
    private void agregar(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) lblOut.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/agregarDocente.fxml"));
        Parent pane = fxmlLoader.load();
        agregarDocente controlador = (agregarDocente) fxmlLoader.getController();
        controlador.configurar(true, null);
        stageTheLabelBelongs.getScene().setRoot(pane);
    }
    @FXML
    private void editar(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) lblOut.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/agregarDocente.fxml"));
        Parent pane = fxmlLoader.load();
        agregarDocente controlador = (agregarDocente) fxmlLoader.getController();
        controlador.configurar(false, tabla.getSelectionModel().getSelectedItem());
        stageTheLabelBelongs.getScene().setRoot(pane);
    }
    @FXML
    private void seleccionar() {
        Docente sel = tabla.getSelectionModel().getSelectedItem();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(sel.getFoto()));
        Image img = new Image(inputStream);
        foto.setImage(img);
    }
    @FXML
    private void Pant_Grupo(ActionEvent event) throws IOException {
        Stage stageTheLabelBelongs = (Stage) lblOut.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/primaryGrupo.fxml"));
        Parent pane = fxmlLoader.load();
        stageTheLabelBelongs.getScene().setRoot(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombre.setCellValueFactory(cellData -> cellData.getValue().NameProperty());
        apellidos.setCellValueFactory(cellData -> cellData.getValue().ApellidoProperty());
        correo.setCellValueFactory(cellData -> cellData.getValue().CorreoProperty());
        fecha.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
        // Obtenemos los datos a ser mostrados en nuestro Grilla Visual
        try {
            tabla.setItems(getData());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    private ObservableList<Docente> getData() throws ParseException {
        // Creamos una Lista de Docentes a ser devuelta por el método
        ObservableList<Docente> Data = FXCollections.observableArrayList();
        // Iniciamos nuestro Cliente HTTP para Microservicio REST
        HttpClient client = HttpClient.newHttpClient();
        // Configuramos nuestro requerimiento, estipulando trabajar con JSON
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8080/docentes"))
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
            JSONArray Lista = (JSONArray) Datos.get("docentes");
            for (int i=0;i<Lista.size();i++) {
                // Obtenemos cada uno de los elementos contenidos en el Array
                JSONObject Item = (JSONObject) Lista.get(i);
                // Obtenemos el ID del Objeto que se presenta en "_links.self.href"
                String cod = ((JSONObject) ((JSONObject) Item.get("_links")).get("self")).get("href").toString();
                Docente dato = new Docente(); //Creo que Objeto Docente para setear sus atributos
                dato.setId(Long.parseLong(cod.substring(cod.lastIndexOf("/")+1, cod.length())));
                dato.setName(Item.get("nombre").toString());
                dato.setApellido(Item.get("apellido").toString());
                dato.setCorreo(Item.get("email").toString());
                dato.setBirthday(LocalDate.parse(Item.get("nacimiento").toString().substring(0, 10)));
                // Si cuenta con foto almacenada la carga, sino una cadena vacia
                if(Item.get("foto")!=null) dato.setFoto(Item.get("foto").toString());
                else dato.setFoto("");
                // Añadimos el elemento a nuestra lista que será devuelta por el método
                Data.add(dato);
            }
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return Data;
    }
}
