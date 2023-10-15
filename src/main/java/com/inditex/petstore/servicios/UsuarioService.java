package com.inditex.petstore.servicios;

import com.inditex.petstore.usuarios.Usuario;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * UsuarioService es una clase que proporciona métodos para interactuar con un servicio web que gestiona usuarios.
 *
 * Utiliza el servicio web de PetStore para crear usuarios y consultar información de usuarios existentes.
 */
public class UsuarioService {
    private final String baseUrl = "https://petstore.swagger.io/v2/user/";
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Crea un nuevo usuario en el servicio web.
     *
     * @param usuario El objeto Usuario que se desea crear.
     */
    public void crearUsuario(Usuario usuario) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(baseUrl);

            // Convierte el objeto Usuario a formato JSON
            String usuarioJson = objectMapper.writeValueAsString(usuario);

            // Configura la solicitud con el encabezado y el cuerpo JSON
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(usuarioJson));

            // Realiza la solicitud HTTP POST
            HttpResponse response = httpClient.execute(httpPost);

            // Verifica la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("Usuario creado exitosamente.");
            } else {
                System.out.println("Error al crear el usuario. Código de respuesta: " + statusCode);
                String responseJson = EntityUtils.toString(response.getEntity());
                System.out.println("Respuesta del servidor: " + responseJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Consulta un usuario por su nombre de usuario.
     *
     * @param username El nombre de usuario del usuario que se desea consultar.
     * @return Un objeto Usuario si se encuentra, o null en caso de error o si no se encuentra.
     */
    public Usuario consultarUsuarioPorNombre(String username) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(baseUrl + username);

        try {
            HttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                // Procesa la respuesta y conviértela a un objeto Usuario
                Usuario usuario = objectMapper.readValue(response.getEntity().getContent(), Usuario.class);
                return usuario;
            } else {
                // Maneja errores
                System.err.println("Error al consultar el usuario. Código de respuesta: " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            // Maneja excepciones
            e.printStackTrace();
        }

        return null;
    }
}
