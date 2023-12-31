package com.inditex.petstore.servicios;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.petstore.servicios.UsuarioService;
import com.inditex.petstore.usuarios.Usuario;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.entity.StringEntity;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;

/**
 * La clase UsuarioServiceTest contiene pruebas unitarias para el servicio UsuarioService.
 *
 * Cada prueba verifica diferentes escenarios relacionados con la consulta de usuarios por nombre y el manejo de errores.
 */
public class UsuarioServiceTest {
    private UsuarioService servicio;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        servicio = new UsuarioService(); // Asegúrate de instanciar y configurar tu servicio adecuadamente
        objectMapper = new ObjectMapper(); // Asegúrate de configurar ObjectMapper adecuadamente
    }

    /**
     * Prueba que el servicio pueda consultar un usuario existente y devolver el usuario esperado.
     *
     * @throws Exception Si ocurre un error inesperado durante la ejecución de la prueba.
     */
    @Test
    public void testConsultarUsuarioExistente() throws Exception {
        HttpClient httpClient = mock(HttpClient.class);
        HttpResponse response = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);

        Usuario usuarioEsperado = new Usuario();
        usuarioEsperado.setId(32920);
        usuarioEsperado.setUsername("pedro_pablo2");
        usuarioEsperado.setFirstName("pedro");
        usuarioEsperado.setLastName("pablo");
        usuarioEsperado.setEmail("pablo@pedro.com");
        usuarioEsperado.setPassword("pedro");
        usuarioEsperado.setPhone("325453");
        usuarioEsperado.setUserStatus(0);

        when(httpClient.execute(any(HttpGet.class))).thenReturn(response);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getEntity()).thenReturn(new StringEntity(objectMapper.writeValueAsString(usuarioEsperado)));

        Usuario resultado = servicio.consultarUsuarioPorNombre("pedro_pablo2");
        assertEquals(usuarioEsperado, resultado);
    }

    /**
     * Prueba que el servicio maneje adecuadamente la situación en la que no se encuentra un usuario con el nombre especificado.
     *
     * @throws Exception Si ocurre un error inesperado durante la ejecución de la prueba.
     */
    @Test
    public void testConsultarUsuarioNoExistente() throws Exception {
        HttpClient httpClient = mock(HttpClient.class);
        HttpResponse response = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);

        when(httpClient.execute(any(HttpGet.class))).thenReturn(response);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(404);

        Usuario resultado = servicio.consultarUsuarioPorNombre("UsuarioNoExistente");
        assertNull(resultado);
    }

    /**
     * Prueba que el servicio maneje adecuadamente un error de red y devuelva un resultado nulo.
     *
     * @throws Exception Si ocurre un error inesperado durante la ejecución de la prueba.
     */
    @Test
    public void testErrorDeRed() throws Exception {
        HttpClient httpClient = mock(HttpClient.class);
        HttpGet httpGet = mock(HttpGet.class);

        when(httpGet.getURI()).thenReturn(new URI("https://petstore.swagger.io/v2/user/string"));

        when(httpClient.execute(httpGet)).thenThrow(new IOException("Error de red"));

        ByteArrayOutputStream errorOutput = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorOutput));

        Usuario resultado = servicio.consultarUsuarioPorNombre("UsuarioDePrueba");

        // Verificar que el resultado sea null (ya que ocurrió un error)
        assertNull(resultado);
    }
}
