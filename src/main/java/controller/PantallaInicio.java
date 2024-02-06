    package controller;

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.stage.Stage;

    import java.io.IOException;

    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.stage.Stage;

    public class PantallaInicio {
        @FXML
        private Button btnStarApp; // Esta variable se asocia al botón mediante el fx:id

        @FXML
        private void irAOtraPagina(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/buscador.fxml"));
                Parent otraPaginaParent = loader.load();
                Scene otraPaginaScene = new Scene(otraPaginaParent);

                Stage escenarioActual = (Stage) btnStarApp.getScene().getWindow();
                escenarioActual.setScene(otraPaginaScene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

