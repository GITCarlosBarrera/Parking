package com.example.parking.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa un registro de entrada y salida de un vehículo en el parking.
 * Almacena la información del vehículo, la hora de entrada y la hora de salida.
 *
 * @author GITCarlosBarrera
 */
public class RegistroParking {
    private Vehiculo vehiculo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;

    /**
     * Crea un nuevo registro de parking con la hora de entrada y el vehículo asociado.
     *
     * @param horaEntrada la hora de entrada al parking
     * @param vehiculo el vehículo registrado
     */
    public RegistroParking(LocalDateTime horaEntrada, Vehiculo vehiculo) {
        this.horaEntrada = horaEntrada;
        this.vehiculo = vehiculo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * Devuelve una representación en texto del registro, mostrando el vehículo y las horas.
     *
     * @return una cadena con la información del registro
     */
    @Override
    public String toString() {
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        return vehiculo.toString() + " --> " + horaEntrada.format(formatoHora)
                + (horaSalida != null ? "/" + horaSalida.format(formatoHora) : "");
    }
}
