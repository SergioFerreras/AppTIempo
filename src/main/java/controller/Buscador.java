package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Buscador {
    @FXML
    private Button btnAtras; // Esta variable se asocia al botón mediante el fx:id
    @FXML
    private Button btnBuscar; // Esta variable se asocia al botón mediante el fx:id
    @FXML
    private Button btnAñadir; // Esta variable se asocia al botón mediante el fx:id
    @FXML
    private TextField buscador; // Asociación con el TextField

    @FXML
    private ListView<String> listaCiudades;

    public void agregarCiudad(String ciudad) {
        listaCiudades.getItems().add(ciudad);
    }
    @FXML
    private void abrirDetallesCiudad(ActionEvent event) {
        String ciudadSeleccionada = listaCiudades.getSelectionModel().getSelectedItem();
        if (ciudadSeleccionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/tiempo.fxml"));
                Parent otraPaginaParent = loader.load();

                // Obtén el controlador después de cargar la interfaz gráfica
                Tiempo detallesCiudadController = loader.getController();

                // Pasa el nombre de la ciudad seleccionada al controlador de la siguiente página
                detallesCiudadController.setNombreCiudad(ciudadSeleccionada);

                Scene otraPaginaScene = new Scene(otraPaginaParent);

                Stage escenarioActual = (Stage) listaCiudades.getScene().getWindow();
                escenarioActual.setScene(otraPaginaScene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void irAtras(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/pantalla_inicio.fxml"));
            Parent otraPaginaParent = loader.load();
            Scene otraPaginaScene = new Scene(otraPaginaParent);

            Stage escenarioActual = (Stage) btnAtras.getScene().getWindow();
            escenarioActual.setScene(otraPaginaScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buscarCiudad(ActionEvent event) {
        try {
            String textoBusqueda = buscador.getText();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/tiempo.fxml"));
            Parent otraPaginaParent = loader.load();

            Scene otraPaginaScene = new Scene(otraPaginaParent);

            // Obtén el controlador después de cargar la interfaz gráfica
            Tiempo pagBuscadorController = loader.getController();

            // Pasa el texto de búsqueda al controlador de la siguiente página
            pagBuscadorController.setNombreCiudad(textoBusqueda);

            Stage escenarioActual = (Stage) btnBuscar.getScene().getWindow();
            escenarioActual.setScene(otraPaginaScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void añadirCiudad(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/añadirCiudad.fxml"));
            Parent otraPaginaParent = loader.load();

            Scene otraPaginaScene = new Scene(otraPaginaParent);

            Stage escenarioActual = (Stage) btnBuscar.getScene().getWindow();
            escenarioActual.setScene(otraPaginaScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
