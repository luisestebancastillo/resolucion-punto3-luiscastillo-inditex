package com.inditex.petstore;

import com.inditex.petstore.mascotas.Mascota;
import com.inditex.petstore.servicios.MascotaService;
import com.inditex.petstore.servicios.UsuarioService;
import com.inditex.petstore.usuarios.Usuario;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        UsuarioService usuarioService = new UsuarioService();
        MascotaService mascotaService = new MascotaService();


        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setUsername("Luchocd27");
        usuario.setFirstName("Luis");
        usuario.setLastName("Castillo");
        usuario.setEmail("luchocd27@gmail.com");
        usuario.setPassword("123456789");
        usuario.setPhone("573214539591");
        usuario.setUserStatus(1);

        usuarioService.crearUsuario(usuario);
        String usernameConsultar = usuario.getUsername();
        Usuario usuarioConsultado = usuarioService.consultarUsuarioPorNombre(usernameConsultar);

        if (usuarioConsultado != null) {
            System.out.println("Usuario consultado: " + usuarioConsultado);
        } else {
            System.out.println("No se encontró el usuario.");
        }

        List<Mascota> mascotasVendidas = mascotaService.obtenerMascotasPorStatus("sold");

        if (mascotasVendidas != null && !mascotasVendidas.isEmpty()) {
            System.out.println("Mascotas vendidas:");

            for (Mascota mascota : mascotasVendidas) {
                System.out.println("{id: " + mascota.getId() + ", name: " + mascota.getName() + "}");
            }
        } else {
            System.out.println("No se encontraron mascotas vendidas.");
        }

        ContadorNombresMascotas contador = new ContadorNombresMascotas(mascotasVendidas);
        Map<String, Long> resultado = contador.contarNombresMascotas();


        System.out.print("{");
        for (Map.Entry<String, Long> entry : resultado.entrySet()) {
            System.out.print("“" + entry.getKey() + "”: " + entry.getValue() + ", ");
        }
        System.out.print("}");
    }
}
