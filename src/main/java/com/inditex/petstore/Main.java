package com.inditex.petstore;

import com.inditex.petstore.servicios.UsuarioService;
import com.inditex.petstore.usuarios.Usuario;

public class Main {
    public static void main(String[] args) {

        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setUsername("Luchocd27");
        usuario.setFirstName("Luis");
        usuario.setLastName("Castillo");
        usuario.setEmail("luchocd27@gmail.com");
        usuario.setPassword("123456789");
        usuario.setPhone("573214539591");
        usuario.setUserStatus(1);

        UsuarioService usuarioService = new UsuarioService();
        usuarioService.crearUsuario(usuario);

        String usernameConsultar = usuario.getUsername();
        Usuario usuarioConsultado = usuarioService.consultarUsuarioPorNombre(usernameConsultar);

        if (usuarioConsultado != null) {
            System.out.println("Usuario consultado: " + usuarioConsultado);
        } else {
            System.out.println("No se encontró el usuario.");
        }
    }
}
