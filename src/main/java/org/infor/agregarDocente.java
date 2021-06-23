package org.infor;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Base64;
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

public class agregarDocente implements Initializable {
    String img_json="";
    private Docente X;
    private Boolean nuevo=true;

    @FXML
    TextField input_nombre;
    @FXML
    TextField input_apellido;
    @FXML
    TextField input_email;
    @FXML
    DatePicker input_nac;
    @FXML
    Button btnExit;
    @FXML
    ImageView img_doc;

    public void configurar(Boolean action, Docente D) {
        nuevo = action; X = D;
        System.out.println(nuevo);
        if (nuevo) { X = new Docente(); }
        else {
            input_nombre.setText(X.getName());input_apellido.setText(X.getApellido());
            input_email.setText(X.getCorreo());input_nac.setValue(X.getBirthday());
            img_json = X.getFoto();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(img_json));
            Image img = new Image(inputStream);
            img_doc.setImage(img);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    @FXML
    private void subir_foto() throws IOException {
        // Crear el Cuadro de Dialogo de selección de Archivo
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.jpg*",".png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        
        // Obtener la imagen seleccionada
        Stage main = (Stage) btnExit.getScene().getWindow();
        File imgFile = fileChooser.showOpenDialog(main);
        // Cargar la imagen desde el fichero
        FileInputStream fileInputStreamReader = new FileInputStream(imgFile);
        byte[] bytes = new byte[(int)imgFile.length()];
        fileInputStreamReader.read(bytes);fileInputStreamReader.close();
        // Codificar en Base64 para incluirla en un Objeto JSON
        img_json = new String(Base64.getEncoder().encodeToString(bytes));
        // Cargar imagen desde un String Base64
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(img_json));
        Image img = new Image(inputStream);
        img_doc.setImage(img);
    }
    @FXML
    private void btnClick(ActionEvent event) throws ParseException  {
        // Creamos nuestra instancia de Docente con los datos de la TextField
        X.setName(input_nombre.getText());X.setApellido(input_apellido.getText());;X.setCorreo(input_email.getText());
        X.setBirthday(input_nac.getValue());X.setFoto(img_json);
        // Parseamos nuestro Objeto Docente en un JSON Objetc
        JSONObject requestJSON = (JSONObject) new JSONParser().parse(X.toString());
        // POST y PUT JSON
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/docentes"))
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
