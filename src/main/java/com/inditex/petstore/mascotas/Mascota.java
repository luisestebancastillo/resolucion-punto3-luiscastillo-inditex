package com.inditex.petstore.mascotas;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Mascota {
    private long id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls;
    public ArrayList<Tag> tags;
    public String status;
}