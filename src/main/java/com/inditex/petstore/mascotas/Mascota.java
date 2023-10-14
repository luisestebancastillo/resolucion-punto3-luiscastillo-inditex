package com.inditex.petstore.mascotas;

import lombok.Data;

@Data
public class Mascota {
    private long id;
    private String nombre;
    private String categoria;
    private String estado;
}