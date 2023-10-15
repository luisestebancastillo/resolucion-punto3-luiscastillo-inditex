package com.inditex.petstore;

import com.inditex.petstore.mascotas.Mascota;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContadorNombresMascotas {
    private List<Mascota> mascotas;

    public ContadorNombresMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public Map<String, Long> contarNombresMascotas() {
        Map<String, Long> resultado = new HashMap<>();

        for (Mascota mascota : mascotas) {
            String nombre = mascota.getName();
            resultado.put(nombre, resultado.getOrDefault(nombre, 0L) + 1);
        }

        return resultado;
    }

}
