package com.inditex.petstore.servicios;

import com.inditex.petstore.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class UsuarioServiceTest {

    @Mock
    private UsuarioService usuarioService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCrearUsuario() {
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario();
        usuario.setId(0);
        usuario.setUsername("string");
        usuario.setFirstName("string");
        usuario.setLastName("string");
        usuario.setEmail("string");
        usuario.setPassword("string");
        usuario.setPhone("string");
        usuario.setUserStatus(0);

        doNothing().when(usuarioService).crearUsuario(usuario); // Configura el comportamiento del método

        usuarioService.crearUsuario(usuario); // Llama al método

        verify(usuarioService).crearUsuario(usuario);
    }
}

