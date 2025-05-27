package com.example.parking.vehiculos;

public class VResidente extends Vehiculo {
    private long minutosEnParking;

    public VResidente(String matricula) {
        super(matricula, "Residente");
        minutosEnParking = 0;
    }

    @Override
    public String toString() {
        return getMatricula() + " - \'" + getTipoVehiculo() + '\'';
    }

    public long getMinutosEnParking() {
        return minutosEnParking;
    }

    public void setMinutosEnParking(long minutos) {
        this.minutosEnParking = minutos;
    }

    public void addMinutosEnParking(long minutos) {
        minutosEnParking += minutos;
    }
}
