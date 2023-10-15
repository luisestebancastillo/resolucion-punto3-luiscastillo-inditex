package com.inditex.petstore.mascotas;

import lombok.Data;

/**
 * La clase Category representa una categoría de elementos.
 *
 * Cada categoría tiene un identificador único y un nombre que la describe.
 */
@Data
public class Category {
    /**
     * El identificador único de la categoría.
     */
    public int id;

    /**
     * El nombre que describe la categoría.
     */
    public String name;
}
