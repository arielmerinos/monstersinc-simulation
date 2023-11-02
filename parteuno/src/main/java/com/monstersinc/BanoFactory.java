package com.monstersinc;

public class BanoFactory {
    public static Bano createBano(String tipoMonstruo) {
        switch(tipoMonstruo) {
            case "Grande":
                return new BanoGrande();
            case "Pequeno":
                return new BanoPequeno();
            default:
                throw new IllegalArgumentException("Tipo de monstruo no soportado");
        }
    }
}