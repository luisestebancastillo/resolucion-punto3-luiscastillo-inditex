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

/**
 * MascotaService es una clase que proporciona métodos para interactuar con un servicio web que gestiona mascotas.
 *
 * Utiliza el servicio web de PetStore para obtener información sobre mascotas en función de su estado.
 */
public class MascotaService {

    private final String baseUrl = "https://petstore.swagger.io/v2/pet/";

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Obtiene una lista de mascotas en función de su estado.
     *
     * @param status El estado de las mascotas a consultar.
     * @return Una lista de mascotas que coinciden con el estado especificado.
     *         Si no se encuentra ninguna mascota, se devuelve una lista vacía.
     *         En caso de error o excepción, se devuelve null.
     */
    public List<Mascota> obtenerMascotasPorStatus(String status) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(baseUrl + "findByStatus?status=" + status);

        try {
            HttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                // Procesar la respuesta y convertirla a una lista de mascotas
                List<Mascota> mascotas = objectMapper.readValue(response.getEntity().getContent(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Mascota.class));
                return mascotas;
            } else {
                // Manejar errores y mostrar un mensaje en el sistema de error estándar
                System.err.println("Error al consultar mascotas. Código de respuesta: " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            // Manejar excepciones y mostrar información de la excepción en el sistema de error estándar
            e.printStackTrace();
        }

        return null;
    }
}


