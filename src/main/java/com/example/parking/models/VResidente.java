package com.example.parking.models;

/**
 * Representa un vehículo de tipo residente en el sistema de parking.
 * Almacena la matrícula y el tiempo total que ha permanecido en el parking.
 *
 * @author GITCarlosBarrera
 */
public class VResidente extends Vehiculo {
    private long minutosEnParking;

    /**
     * Crea un nuevo vehículo residente con la matrícula especificada.
     *
     * @param matricula la matrícula del vehículo
     */
    public VResidente(String matricula) {
        super(matricula, "Residente");
        minutosEnParking = 0;
    }

    public long getMinutosEnParking() {
        return minutosEnParking;
    }

    public void setMinutosEnParking(long minutos) {
        this.minutosEnParking = minutos;
    }

    /**
     * Suma minutos al tiempo total que el vehículo ha permanecido en el parking.
     *
     * @param minutos minutos a añadir
     */
    public void addMinutosEnParking(long minutos) {
        minutosEnParking += minutos;
    }

    /**
     * Devuelve una representación en texto del vehículo residente.
     *
     * @return una cadena con la matrícula y el tipo de vehículo
     */
    @Override
    public String toString() {
        return getMatricula() + " - \'" + getTipoVehiculo() + '\'';
    }
}
