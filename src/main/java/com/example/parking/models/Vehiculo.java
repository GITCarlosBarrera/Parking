package com.example.parking.models;

/**
 * Clase abstracta que representa un vehículo en el sistema de parking.
 * Almacena la matrícula y el tipo de vehículo.
 * Debe ser extendida por tipos concretos de vehículos.
 *
 * @author GITCarlosBarrera
 */
public abstract class Vehiculo {
    private String matricula;
    private String tipoVehiculo;

    /**
     * Crea un nuevo vehículo con la matrícula y el tipo especificados.
     *
     * @param matricula la matrícula del vehículo
     * @param tipoVehiculo el tipo de vehículo
     */
    public Vehiculo(String matricula, String tipoVehiculo) {
        this.matricula = matricula;
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    /**
     * Devuelve una representación en texto del vehículo.
     * Debe ser implementado por las subclases.
     *
     * @return una cadena con la información relevante del vehículo
     */
    public abstract String toString();
}
