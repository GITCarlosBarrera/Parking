package com.example.parking;

import com.example.parking.vehiculos.VNoResidente;
import com.example.parking.vehiculos.VOficial;
import com.example.parking.vehiculos.VResidente;
import com.example.parking.vehiculos.Vehiculo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class RegistrosController {
    private Controller controller;
    private String modo;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    @FXML private TextField matriculaTF;

    @FXML
    private void buscarMatricula() {
        String matricula = matriculaTF.getText().trim();

        Stage stage = (Stage) matriculaTF.getScene().getWindow();

        switch (modo) {
            case "ENTRADA":
                Vehiculo v = controller.buscarVehiculo(matricula);
                if (v != null) {
                    controller.registrarEntrada(new RegistroParking(LocalDateTime.now(), v));
                    stage.close();
                } else if (matricula.matches("^[0-9]{4}-[A-Z]{3}$")) {
                    v = new VNoResidente(matricula);
                    controller.registrarEntrada(new RegistroParking(LocalDateTime.now(), v));
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
                int index = controller.buscarRegistroParking(matricula);
                if (index > -1) {
                    controller.registrarSalida(index);
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
