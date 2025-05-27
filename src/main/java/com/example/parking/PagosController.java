package com.example.parking;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PagosController {
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML private TextField nombreInforme;

    @FXML
    private void crearInforme() throws IOException {
        String nombre = nombreInforme.getText().trim().replaceAll("\\s+", "");

        if (nombre != "" && nombre != null && !nombre.matches("^.+\\..+$")) {
            controller.crearInforme(nombre);

            Stage stage = (Stage) nombreInforme.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nombre invalido");
            alert.setContentText("Introduzca el un nombre para el archivo sin su extension");
            alert.showAndWait();
        }
    }
}
