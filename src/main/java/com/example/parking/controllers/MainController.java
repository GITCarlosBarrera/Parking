package com.example.parking.controllers;

import com.example.parking.models.RegistroParking;
import com.example.parking.models.VNoResidente;
import com.example.parking.models.VOficial;
import com.example.parking.models.VResidente;
import com.example.parking.models.Vehiculo;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;

/**
 * Controlador principal de la aplicación JavaFX para la gestión de vehículos en el parking.
 * Gestiona la lógica de la interfaz gráfica, incluyendo la interacción con los elementos visuales
 * y la manipulación de datos relacionados con los vehículos y sus registros.
 *
 * @author GITCarlosBarrera
 */
public class MainController {
    @FXML private Label horaLabel;

    private final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML private ComboBox<String> listaVehiculosCB;
    @FXML private ListView<Vehiculo> vehiculosRegistradosLV;
    @FXML private ListView<RegistroParking> tiempoVehiculosLV;
    @FXML private Button eliminarButton;

    private ObservableList<Vehiculo> vehiculosExistentes;
    private ObservableList<RegistroParking> parkingVehiculos;

    /**
     * Inicializa los elementos de la interfaz gráfica y configura el reloj en tiempo real.
     */
    @FXML
    private void initialize() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> actualizarHora()),
                new KeyFrame(Duration.seconds(1), e -> actualizarHora())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        listaVehiculosCB.getItems().setAll("Vehiculos Registrados", "Tiempo de Vehiculos");
        listaVehiculosCB.setValue("Vehiculos Registrados");

        tiempoVehiculosLV.setVisible(false);
        tiempoVehiculosLV.setManaged(false);

        vehiculosExistentes = FXCollections.observableArrayList();
        vehiculosRegistradosLV.setItems(vehiculosExistentes);

        parkingVehiculos = FXCollections.observableArrayList();
        tiempoVehiculosLV.setItems(parkingVehiculos);

    }

    /**
     * Abre la ventana para dar de alta un nuevo vehículo.
     */
    @FXML
    private void abrirAlta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("alta.fxml"));
            Parent root = loader.load();

            AltaController altaController = loader.getController();
            altaController.setController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Abre la ventana para registrar la entrada de un vehículo.
     */
    @FXML
    private void abrirRegistrarEntrada() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registros.fxml"));
            Parent root = loader.load();

            RegistrosController registrosController = loader.getController();
            registrosController.setController(this);
            registrosController.setModo("ENTRADA");

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Abre la ventana para registrar la salida de un vehículo.
     */
    @FXML
    private void abrirRegistrarSalida() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registros.fxml"));
            Parent root = loader.load();

            RegistrosController registrosController = loader.getController();
            registrosController.setController(this);
            registrosController.setModo("SALIDA");

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Abre la ventana para mostrar los pagos de los residentes.
     */
    @FXML
    private void mostrarPagosResidentes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pagos.fxml"));
            Parent root = loader.load();

            PagosController pagosController = loader.getController();
            pagosController.setController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reinicia los datos de los vehículos oficiales y residentes al comenzar un nuevo mes.
     */
    @FXML
    private void comenzarMes() {
        Iterator<RegistroParking> it = parkingVehiculos.iterator();

        while (it.hasNext()) {
            RegistroParking rp = it.next();
            if (rp.getVehiculo() instanceof VOficial) {
                it.remove();
            } else if (rp.getVehiculo() instanceof VResidente) {
                ((VResidente) rp.getVehiculo()).setMinutosEnParking(0);
            }
        }
    }

    /**
     * Cambia la lista visible en la interfaz gráfica según la selección del usuario.
     */
    @FXML
    private void cambiarLista() {
        String seleccion = listaVehiculosCB.getValue();

        switch (seleccion) {
            case "Vehiculos Registrados":
                vehiculosRegistradosLV.setVisible(true);
                vehiculosRegistradosLV.setManaged(true);
                tiempoVehiculosLV.setVisible(false);
                tiempoVehiculosLV.setManaged(false);
                eliminarButton.setVisible(true);
                eliminarButton.setManaged(true);
                break;

            case "Tiempo de Vehiculos":
                vehiculosRegistradosLV.setVisible(false);
                vehiculosRegistradosLV.setManaged(false);
                tiempoVehiculosLV.setVisible(true);
                tiempoVehiculosLV.setManaged(true);
                eliminarButton.setVisible(false);
                eliminarButton.setManaged(false);
                break;
        }
    }

    /**
     * Elimina un vehículo seleccionado de la lista de vehículos registrados.
     */
    @FXML
    private void eliminarVehiculo() {
        Vehiculo vehiculoSeleccionado = vehiculosRegistradosLV.getSelectionModel().getSelectedItem();
        if (vehiculoSeleccionado != null) {
            vehiculosExistentes.remove(vehiculoSeleccionado);
        }
    }

    /**
     * Agrega un vehículo a la lista de vehículos registrados.
     *
     * @param v el vehículo a agregar
     */
    public void agregarVehiculo(Vehiculo v) {
        vehiculosExistentes.add(v);
    }

    /**
     * Busca un vehículo por su matrícula en la lista de vehículos registrados.
     *
     * @param matricula la matrícula del vehículo a buscar
     * @return el vehículo encontrado o null si no existe
     */
    public Vehiculo buscarVehiculo(String matricula) {
        for (Vehiculo v : vehiculosExistentes) {
            if (v.getMatricula().equals(matricula)) return v;
        }
        return null;
    }

    /**
     * Busca un registro de parking por la matrícula del vehículo.
     *
     * @param matricula la matrícula del vehículo
     * @return el índice del registro encontrado o -1 si no existe
     */
    public int buscarRegistroParking(String matricula) {
        int cont = 0;
        for (RegistroParking rp : parkingVehiculos) {
            if (rp.getVehiculo().getMatricula().equals(matricula) && rp.getHoraSalida() == null) {
                return cont;
            } else {
                cont++;
            }
        }
        return -1;
    }

    /**
     * Registra la entrada de un vehículo al parking.
     *
     * @param rp el registro de parking a agregar
     */
    public void registrarEntrada(RegistroParking rp) {
        if (rp.getHoraSalida() == null) {
            parkingVehiculos.add(rp);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Vehiculo ya registrado");
            alert.setContentText("El vehiculo ya tiene una entrada registrada en el parking.");
            alert.showAndWait();
        }
    }

    /**
     * Registra la salida de un vehículo del parking.
     *
     * @param index el índice del registro de parking
     */
    public void registrarSalida(int index) {
        RegistroParking rp = parkingVehiculos.get(index);
        rp.setHoraSalida(LocalDateTime.now());

        LocalDateTime entrada = rp.getHoraEntrada();
        LocalDateTime salida = rp.getHoraSalida();

        if (rp.getVehiculo() instanceof VResidente) {
            ((VResidente) rp.getVehiculo()).addMinutosEnParking(ChronoUnit.MINUTES.between(entrada, salida));
        } else if (rp.getVehiculo() instanceof VNoResidente) {
            double precio = ChronoUnit.MINUTES.between(entrada, salida) * 0.02;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Importe");
            alert.setHeaderText("Precio a pagar:" + precio + "€");
            alert.setContentText("Pague en taquilla al salir.");
            alert.getButtonTypes().setAll(new ButtonType("Pagar", ButtonBar.ButtonData.OK_DONE));
            alert.showAndWait();
        }


    }

    /**
     * Crea un informe en formato CSV con los datos de los vehículos residentes.
     *
     * @param nombre el nombre del archivo a generar
     * @throws IOException si ocurre un error al escribir el archivo
     */
    public void crearInforme(String nombre) throws IOException {
        File f = new File(nombre + ".csv");
        FileWriter fw = new FileWriter(f);
        Vehiculo v;
        String matricula;
        long minutos;
        double pago;

        fw.write("Matrícula;Tiempo estacionado (min.); Cantidad a pagar");

        for (RegistroParking rp : parkingVehiculos) {
            v = rp.getVehiculo();
            if (v instanceof VResidente) {
                matricula = v.getMatricula();
                minutos = ((VResidente) v).getMinutosEnParking();
                pago = minutos * 0.002;
                fw.write("\n" + matricula + ";" + minutos + ";" + pago);
            }
        }

        fw.close();
    }

    /**
     * Actualiza la hora mostrada en la interfaz gráfica.
     */
    private void actualizarHora() {
        horaLabel.setText(java.time.LocalTime.now().format(formatoHora));
    }
}
