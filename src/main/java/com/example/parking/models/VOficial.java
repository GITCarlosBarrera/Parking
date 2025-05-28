package com.example.parking.models;

/**
 * Representa un vehículo de tipo oficial en el sistema de parking.
 * Almacena la matrícula y el tipo de vehículo.
 *
 * @author GITCarlosBarrera
 */
public class VOficial extends Vehiculo {

    /**
     * Crea un nuevo vehículo oficial con la matrícula especificada.
     *
     * @param matricula la matrícula del vehículo
     */
    public VOficial(String matricula) {
        super(matricula, "Oficial");
    }

    /**
     * Devuelve una representación en texto del vehículo oficial.
     *
     * @return una cadena con la matrícula y el tipo de vehículo
     */
    @Override
    public String toString() {
        return getMatricula() + " - \'" + getTipoVehiculo() + '\'';
    }
}
