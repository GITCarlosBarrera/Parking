package com.example.parking.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación JavaFX para la gestión de parking.
 * Inicializa la interfaz gráfica principal cargando el archivo FXML y aplicando los estilos CSS.
 *
 * @author GITCarlosBarrera
 * @version 1.1
 */
public class MainApp extends javafx.application.Application {

    /**
     * Punto de entrada de JavaFX. Configura y muestra la ventana principal.
     *
     * @param stage escenario principal proporcionado por JavaFX
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 440);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("480 PARKING");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método main. Lanza la aplicación JavaFX.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        launch();
    }
}