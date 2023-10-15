package com.inditex.petstore.mascotas;

import lombok.Data;

/**
 * La clase Tag representa una etiqueta asociada a un elemento.
 *
 * Cada etiqueta tiene un identificador único y un nombre que la describe.
 */
@Data
public class Tag {
    /**
     * El identificador único de la etiqueta.
     */
    public int id;

    /**
     * El nombre que describe la etiqueta.
     */
    public String name;
}
