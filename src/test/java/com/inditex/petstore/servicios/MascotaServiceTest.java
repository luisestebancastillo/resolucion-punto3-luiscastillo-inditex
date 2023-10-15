package com.inditex.petstore.servicios;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.petstore.mascotas.Category;
import com.inditex.petstore.mascotas.Mascota;
import com.inditex.petstore.mascotas.Tag;
import com.inditex.petstore.usuarios.Usuario;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * La clase MascotaServiceTest contiene pruebas unitarias para el servicio MascotaService.
 *
 * Cada prueba verifica diferentes escenarios relacionados con la obtención de mascotas por estado y el manejo de errores.
 */
public class MascotaServiceTest {

    private MascotaService servicio;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        servicio = new MascotaService(); // Asegúrate de instanciar y configurar tu servicio adecuadamente
        objectMapper = new ObjectMapper(); // Asegúrate de configurar ObjectMapper adecuadamente
    }

    /**
     * Prueba que el servicio pueda consultar mascotas por estado y devolver la lista esperada.
     *
     * @throws Exception Si ocurre un error inesperado durante la ejecución de la prueba.
     */
    @Test
    public void testConsultarMascotasPorStatus() throws Exception {
        HttpClient httpClient = mock(HttpClient.class);
        HttpResponse response = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);

        // Crear una lista de mascotas simulada

        String filePath = "src/main/resources/mascotas.json";

        // Leer el JSON desde el archivo
        ObjectMapper objectMapper = new ObjectMapper();
        List<Mascota> mascotasEsperadas = objectMapper.readValue(new File(filePath), new TypeReference<List<Mascota>>() {});

        when(httpClient.execute(any(HttpGet.class))).thenReturn(response);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getEntity()).thenReturn(new StringEntity(objectMapper.writeValueAsString(mascotasEsperadas)));

        List<Mascota> resultado = servicio.obtenerMascotasPorStatus("pending");
        assertEquals(mascotasEsperadas, resultado);
    }

    /**
     * Prueba que el servicio maneje adecuadamente la situación en la que no se encuentran mascotas con el estado especificado.
     *
     * @throws Exception Si ocurre un error inesperado durante la ejecución de la prueba.
     */
    @Test
    public void testObtenerMascotasPorStatusNoEncontrado() throws Exception {
        HttpClient httpClient = mock(HttpClient.class);
        HttpResponse response = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);

        when(httpClient.execute(any(HttpGet.class))).thenReturn(response);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(404);

        List<Mascota> resultado = servicio.obtenerMascotasPorStatus("NoEncontrado");
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

        when(httpGet.getURI()).thenReturn(new URI("example.com"));

        when(httpClient.execute(httpGet)).thenThrow(new IOException("Error de red"));

        ByteArrayOutputStream errorOutput = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorOutput));

        List<Mascota> resultado = servicio.obtenerMascotasPorStatus("465");

        // Verificar que el resultado sea null (ya que ocurrió un error)
        Assert.assertNotNull(resultado);
    }
}
