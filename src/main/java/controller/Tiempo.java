package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.PruebaATextoAPI;

import java.net.URL;

public class Tiempo {
    public PruebaATextoAPI metodosApi=new PruebaATextoAPI();
    String nombreCiudad="lol";
    @FXML
    private Label nCiudad; // Esta variable se asocia al botón mediante el fx:id

    @FXML
    private Button btnAtras; // Esta variable se asocia al botón mediante el fx:id

    @FXML
    private Label senTermica; // Esta variable se asocia al botón mediante el fx:id
    @FXML
    private Label temperature; // Esta variable se asocia al botón mediante el fx:id
    @FXML
    private Label tempMax; // Esta variable se asocia al botón mediante el fx:id
    @FXML
    private Label tempMin; // Esta variable se asocia al botón mediante el fx:id
    @FXML
    private Label humidity; // Esta variable se asocia al botón mediante el fx:id
    @FXML
    private Label description; // Esta variable se asocia al botón mediante el fx:id
    @FXML
    private ImageView imagen; // Esta variable se asocia al botón mediante el fx:id
    @FXML
    private AnchorPane miContenedor; // Esta variable se asocia al botón mediante el fx:id

    public Tiempo() {
    }

    public Tiempo(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
        nCiudad.setText(nombreCiudad);
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
        String lista=metodosApi.infoAPI(nombreCiudad);

        if (lista.equals("Error al obtener los datos meteorológicos.")){
            System.err.println("ERORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
        }else{
            setImagen(nombreCiudad);
            nCiudad.setText(nombreCiudad);
            setSenTermica(lista);
            setTemperature(lista);
            setTempMax(lista);
            setTempMin(lista);
            setHumidity(lista);
            setDescription(lista);
            actualizarFondo(metodosApi.sacarlogo(nombreCiudad));

        }
    }

    public void setSenTermica(String lista){
        String senTemp=metodosApi.devolverParametro(lista,"feels_like");
        senTermica.setText(senTemp);
    }

    public void setTemperature(String lista){
        String temperatur=metodosApi.devolverParametro(lista,"temperature");
        temperature.setText(temperatur);
    }

    public void setTempMax(String lista){
        String tempmax=metodosApi.devolverParametro(lista,"tempmax");
        tempMax.setText(tempmax);
    }

    public void setTempMin(String lista){
        String tempmin=metodosApi.devolverParametro(lista,"tempmin");
        tempMin.setText(tempmin);
    }

    public void setHumidity(String lista){
        String humiditty=metodosApi.devolverParametro(lista,"humidity");
        humidity.setText("Humedad: "+humiditty);
    }

    public void setDescription(String lista){
        String descripcion=metodosApi.devolverParametro(lista,"description");
        description.setText("Descripcion: "+descripcion);
    }

    public String getDescripcion(String lista){
        String descripcion=metodosApi.devolverParametro(lista,"description");
        return descripcion;
    }

    public void setImagen(String nombreCiudad){
        String nombreImagen=metodosApi.sacarlogo(nombreCiudad);
        String imagePath=getClass().getResource("/imagenes/" + nombreImagen).toExternalForm();
        if (imagePath != null) {
            Image imagenn = new Image(imagePath);
            imagen.setImage(imagenn);
        } else {
            // Manejar el caso en que no se pueda determinar la ruta de la imagen
            System.err.println("Error al determinar la ruta de la imagen para " + nombreCiudad);
        }
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setStringnCiudad(String nCiudad){
        this.nombreCiudad=nCiudad;
    }

    @FXML
    private void irAtras(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/buscador.fxml"));
            Parent otraPaginaParent = loader.load();
            Scene otraPaginaScene = new Scene(otraPaginaParent);

            Stage escenarioActual = (Stage) btnAtras.getScene().getWindow();
            escenarioActual.setScene(otraPaginaScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarFondo(String estadoTiempo) {
        System.out.println(estadoTiempo);
        String imagenFondo = "url('../imagenesFondo/" + estadoTiempo + "')";
        // Aplicar la imagen de fondo al AnchorPane
        System.out.println(imagenFondo);
        String estiloFondo = "-fx-background-image: " + imagenFondo + ";";
        System.out.println(estiloFondo);


        System.out.println(miContenedor.backgroundProperty());
        miContenedor.setStyle(estiloFondo);
    }

}
