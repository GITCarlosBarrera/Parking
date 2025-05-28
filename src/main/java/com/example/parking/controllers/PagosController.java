package com.example.parking.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador para la ventana de generación de informes de pagos de residentes.
 * Permite al usuario crear un informe CSV con los datos de los pagos.
 *
 * @author GITCarlosBarrera
 */
public class PagosController {
    private MainController mainController;

    /**
     * Establece el controlador principal para la comunicación.
     *
     * @param mainController el controlador principal
     */
    public void setController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML private TextField nombreInforme;

    /**
     * Crea un informe CSV con el nombre proporcionado por el usuario.
     * Muestra un mensaje de error si el nombre no es válido.
     *
     * @throws IOException si ocurre un error al crear el archivo
     */
    @FXML
    private void crearInforme() throws IOException {
        String nombre = nombreInforme.getText().trim().replaceAll("\\s+", "");

        if (nombre != "" && nombre != null && !nombre.matches("^.+\\..+$")) {
            mainController.crearInforme(nombre);

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
