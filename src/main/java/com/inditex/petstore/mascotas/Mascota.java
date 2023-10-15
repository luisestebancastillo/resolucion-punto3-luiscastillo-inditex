package com.inditex.petstore.mascotas;

import lombok.Data;

import java.util.ArrayList;

/**
 * La clase Mascota representa a una mascota.
 *
 * Cada mascota tiene un identificador único, una categoría a la que pertenece,
 * un nombre, una lista de URL de fotos, etiquetas, y un estado.
 */
@Data
public class Mascota {
    /**
     * El identificador único de la mascota.
     */
    private long id;

    /**
     * La categoría a la que pertenece la mascota.
     */
    private Category category;

    /**
     * El nombre de la mascota.
     */
    private String name;

    /**
     * Lista de URLs de fotos de la mascota.
     */
    private ArrayList<String> photoUrls;

    /**
     * Lista de etiquetas asociadas a la mascota.
     */
    public ArrayList<Tag> tags;

    /**
     * El estado actual de la mascota.
     */
    public String status;
}
