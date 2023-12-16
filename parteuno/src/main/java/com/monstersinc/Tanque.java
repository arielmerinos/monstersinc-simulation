package com.monstersinc;

    public  class Tanque implements TanqueI {
        TipoTanque tipo;
        private final String id;
        EstadoTanque estado;
        private int capacidad = 100; // Ej: 100, 200, etc.

        public void setCapacidad(int capacidad) {
            this.capacidad = capacidad;
        }
        public int getCapacidad() {
            return capacidad;
        }
        public String getId() {
            return id;
        }
        public void setEstado(EstadoTanque estado) {
            this.estado = estado;
        }
        
        public Tanque(String id) {
            this.id = id;
            this.estado = EstadoTanque.DISPONIBLE;

        }
        public Tanque(String id, int capacidad) {
            this.id = id;
            this.estado = EstadoTanque.DISPONIBLE;
            this.capacidad = capacidad;
        }

        public Tanque(String id, TipoTanque tipo, int capacidad) {
            this.id = id;
            this.tipo = tipo;
            this.capacidad = capacidad;
        }
        @Override
        public String toString() {
            return "Tanque{" +
                   "id='" + id + '\'' +
                   ", estado=" + estado +
                   ", capacidad=" + capacidad +
                   '}';
        }
        @Override
        public int obtenerCapacidad() {
            // Implement the obtenerCapacidad() method here
            return capacidad;
        }
        @Override
        public String obtenerTipo() {
            // Implement the obtenerTipo() method here
            return tipo.toString();
        }
    }