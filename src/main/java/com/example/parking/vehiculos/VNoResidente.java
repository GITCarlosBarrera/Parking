package com.example.parking.vehiculos;

public class VNoResidente extends Vehiculo {
    public VNoResidente(String matricula) {
        super(matricula, "No Residente");
    }

    @Override
    public String toString() {
        return getMatricula() + " - \'" + getTipoVehiculo() + '\'';
    }
}
