package com.example.parking;

import com.example.parking.vehiculos.VOficial;
import com.example.parking.vehiculos.VResidente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AltaController {
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML private RadioButton vOficialRB;
    @FXML private RadioButton vResidenteRB;

    private ToggleGroup tipoVehiculoTG;

    @FXML private TextField matriculaTF;

    @FXML
    private void initialize() {
        tipoVehiculoTG = new ToggleGroup();

        vOficialRB.setToggleGroup(tipoVehiculoTG);
        vResidenteRB.setToggleGroup(tipoVehiculoTG);
        vOficialRB.setSelected(true);
    }

    @FXML
    private void darDeAlta() {
        RadioButton seleccionTipoVehiculo = (RadioButton) tipoVehiculoTG.getSelectedToggle();
        String tipo = seleccionTipoVehiculo.getText();
        String matricula = matriculaTF.getText();

        if (matricula.matches("^[0-9]{4}-[A-Z]{3}$")) {
            if (tipo.equals("Oficial")) {
                controller.agregarVehiculo(new VOficial(matricula));
            } else {
                controller.agregarVehiculo(new VResidente(matricula));
            }

            Stage stage = (Stage) matriculaTF.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Matricula invalida");
            alert.setContentText("La matrícula debe cumplir el estándar europeo:\n1111-AAA");
            alert.showAndWait();
        }
    }
}
