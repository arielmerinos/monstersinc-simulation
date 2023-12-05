package com.monstersinc;

    public  class Tanque {
        private final String id;
        private final String categoria; // Ej: "Estandar", "Maxitanque", etc.
        private EstadoTanque estado;
        private int capacidad = 100; // Ej: 100, 200, etc.

        public void setCapacidad(int capacidad) {
            this.capacidad = capacidad;
        }
        public int getCapacidad() {
            return capacidad;
        }
        
        public Tanque(String id, String categoria) {
            this.id = id;
            this.categoria = categoria;
            this.estado = EstadoTanque.DISPONIBLE;

        }
        public Tanque(String id, String categoria, int capacidad) {
            this.id = id;
            this.categoria = categoria;
            this.estado = EstadoTanque.DISPONIBLE;
            this.capacidad = capacidad;
        }
        @Override
        public String toString() {
            return "Tanque{" +
                   "id='" + id + '\'' +
                   ", categoria='" + categoria + '\'' +
                   ", estado=" + estado +
                   ", capacidad=" + capacidad +
                   '}';
        }
    }