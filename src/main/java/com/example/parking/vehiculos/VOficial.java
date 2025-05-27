package com.example.parking.vehiculos;

public class VOficial extends Vehiculo {
    public VOficial(String matricula) {
        super(matricula, "Oficial");
    }

    @Override
    public String toString() {
        return getMatricula() + " - \'" + getTipoVehiculo() + '\'';
    }
}
