package com.example.parking.models;

/**
 * Representa un vehículo de tipo no residente en el sistema de parking.
 * Almacena la matrícula y el tipo de vehículo.
 *
 * @author GITCarlosBarrera
 */
public class VNoResidente extends Vehiculo {

    /**
     * Crea un nuevo vehículo no residente con la matrícula especificada.
     *
     * @param matricula la matrícula del vehículo
     */
    public VNoResidente(String matricula) {
        super(matricula, "No Residente");
    }

    /**
     * Devuelve una representación en texto del vehículo no residente.
     *
     * @return una cadena con la matrícula y el tipo de vehículo
     */
    @Override
    public String toString() {
        return getMatricula() + " - \'" + getTipoVehiculo() + '\'';
    }
}
