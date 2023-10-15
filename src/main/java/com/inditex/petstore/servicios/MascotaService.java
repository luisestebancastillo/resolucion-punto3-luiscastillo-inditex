package com.inditex.petstore.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.petstore.mascotas.Mascota;
import com.inditex.petstore.usuarios.Usuario;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MascotaService {

    private final String baseUrl = "https://petstore.swagger.io/v2/pet/";

    private ObjectMapper objectMapper = new ObjectMapper();
    public List<Mascota> obtenerMascotasPorStatus(String status) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(baseUrl +"findByStatus?status="+ status);

        try {
            HttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                // Procesar la respuesta y convertirla a un objeto Usuario
                List<Mascota> mascotas = objectMapper.readValue(response.getEntity().getContent(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Mascota.class));
                return mascotas;
            } else {
                // Manejar errores
                System.err.println("Error al consultar el usuario. Código de respuesta: " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            // Manejar excepciones
            e.printStackTrace();
        }

        return null;
    }
    }


