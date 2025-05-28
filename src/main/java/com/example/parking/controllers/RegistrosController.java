package com.example.parking.controllers;

import com.example.parking.models.RegistroParking;
import com.example.parking.models.VNoResidente;
import com.example.parking.models.Vehiculo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;

/**
 * Controlador para la ventana de registro de entradas y salidas de vehículos en el parking.
 * Permite buscar una matrícula y registrar la entrada o salida según el modo seleccionado.
 *
 * @author GITCarlosBarrera
 */
public class RegistrosController {
    private MainController mainController;
    private String modo;

    /**
     * Establece el controlador principal para la comunicación.
     *
     * @param mainController el controlador principal
     */
    public void setController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Establece el modo de operación: "ENTRADA" o "SALIDA".
     *
     * @param modo el modo de registro
     */
    public void setModo(String modo) {
        this.modo = modo;
    }

    @FXML private TextField matriculaTF;

    /**
     * Busca la matrícula introducida y registra la entrada o salida del vehículo según el modo.
     * Muestra mensajes de error si la matrícula es inválida o no se encuentra.
     */
    @FXML
    private void buscarMatricula() {
        String matricula = matriculaTF.getText().trim();

        Stage stage = (Stage) matriculaTF.getScene().getWindow();

        switch (modo) {
            case "ENTRADA":
                Vehiculo v = mainController.buscarVehiculo(matricula);
                if (v != null) {
                    mainController.registrarEntrada(new RegistroParking(LocalDateTime.now(), v));
                    stage.close();
                } else if (matricula.matches("^[0-9]{4}-[A-Z]{3}$")) {
                    v = new VNoResidente(matricula);
                    mainController.registrarEntrada(new RegistroParking(LocalDateTime.now(), v));
                    stage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Matricula invalida");
                    alert.setContentText("La matrícula debe cumplir el estándar europeo:\n1111-AAA");
                    alert.showAndWait();
                }
                break;
            case "SALIDA":
                int index = mainController.buscarRegistroParking(matricula);
                if (index > -1) {
                    mainController.registrarSalida(index);
                    stage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Matricula no encontrada");
                    alert.setContentText("No se encontro la matricula en el registro del parking.");
                    alert.showAndWait();
                }
                break;
        }

    }
}
