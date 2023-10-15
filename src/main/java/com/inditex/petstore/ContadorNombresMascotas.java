package com.inditex.petstore;

import com.inditex.petstore.mascotas.Mascota;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La clase ContadorNombresMascotas se encarga de contar cuántas veces se repite cada nombre de mascota en una lista de mascotas.
 */
public class ContadorNombresMascotas {
    private List<Mascota> mascotas;

    /**
     * Constructor de la clase ContadorNombresMascotas.
     *
     * @param mascotas La lista de mascotas de la cual se contarán los nombres.
     */
    public ContadorNombresMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    /**
     * Cuenta cuántas veces se repite cada nombre de mascota en la lista de mascotas.
     *
     * @return Un mapa que relaciona cada nombre de mascota con la cantidad de veces que se repite.
     */
    public Map<String, Long> contarNombresMascotas() {
        Map<String, Long> resultado = new HashMap<>();

        for (Mascota mascota : mascotas) {
            String nombre = mascota.getName();
            resultado.put(nombre, resultado.getOrDefault(nombre, 0L) + 1);
        }

        return resultado;
    }
}
