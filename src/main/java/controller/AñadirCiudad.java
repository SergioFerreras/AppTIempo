package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AñadirCiudad {

    private Buscador buscadorController;

    public void setBuscadorController(Buscador buscadorController) {
        this.buscadorController = buscadorController;
    }

    @FXML
    private Button btnAtras;

    @FXML
    private TextField txtNombreCiudad;

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

    @FXML
    private void añadirCiudad(ActionEvent event) {
        String nuevaCiudad = txtNombreCiudad.getText();
        if (!nuevaCiudad.isEmpty()) {
            pasarTextoSiguientePagina(nuevaCiudad);
            txtNombreCiudad.clear(); // Limpiar el TextField después de añadir la ciudad
        }
    }

    private void pasarTextoSiguientePagina(String texto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/buscador.fxml"));
            Parent siguientePaginaParent = loader.load();
            Buscador buscador = loader.getController();
            buscador.agregarCiudad(texto); // Agregar la nueva ciudad al ListView del Buscador

            Scene siguientePaginaScene = new Scene(siguientePaginaParent);

            Stage escenarioActual = (Stage) txtNombreCiudad.getScene().getWindow();
            escenarioActual.setScene(siguientePaginaScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
