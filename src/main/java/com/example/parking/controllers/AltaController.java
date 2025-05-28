package com.example.parking.controllers;

import com.example.parking.models.VOficial;
import com.example.parking.models.VResidente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * Controlador para la ventana de alta de vehículos en la aplicación de parking.
 * Permite registrar nuevos vehículos oficiales o residentes, validando la matrícula
 * y comunicándose con el controlador principal.
 *
 * @author GITCarlosBarrera
 */
public class AltaController {
    private MainController mainController;

    /**
     * Establece el controlador principal para la comunicación.
     *
     * @param mainController el controlador principal
     */
    public void setController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML private RadioButton vOficialRB;
    @FXML private RadioButton vResidenteRB;

    private ToggleGroup tipoVehiculoTG;

    @FXML private TextField matriculaTF;

    /**
     * Inicializa los componentes de la interfaz, agrupando los radio buttons.
     */
    @FXML
    private void initialize() {
        tipoVehiculoTG = new ToggleGroup();

        vOficialRB.setToggleGroup(tipoVehiculoTG);
        vResidenteRB.setToggleGroup(tipoVehiculoTG);
        vOficialRB.setSelected(true);
    }

    /**
     * Da de alta un nuevo vehículo tras validar la matrícula.
     * Muestra un mensaje de error si la matrícula no es válida.
     */
    @FXML
    private void darDeAlta() {
        RadioButton seleccionTipoVehiculo = (RadioButton) tipoVehiculoTG.getSelectedToggle();
        String tipo = seleccionTipoVehiculo.getText();
        String matricula = matriculaTF.getText();

        if (matricula.matches("^[0-9]{4}-[A-Z]{3}$")) {
            if (tipo.equals("Oficial")) {
                mainController.agregarVehiculo(new VOficial(matricula));
            } else {
                mainController.agregarVehiculo(new VResidente(matricula));
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
