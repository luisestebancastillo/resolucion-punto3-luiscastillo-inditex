# Proyecto Resolucion punto 3 Luis Castillo Indetex

Este proyecto está diseñado para interactuar con la API de una tienda de mascotas utilizando el lenguaje de programación Java y la gestión de dependencias Maven.

Se realizó como proyecto Maven y estructura de servicios, los output pueden verse ejecutando la clase Main y adicional hay un screenshot en los resources.

Se usó la libreria http client de apache para el consumo de las apis, jackson databind para serializar los objetos y junit y mockito para las pruebas unitarias.

## Objetivos

Los objetivos de este proyecto son los siguientes:

1. Crear un usuario mediante una solicitud HTTP y recuperar sus datos utilizando el servicio correspondiente.

2. Recopilar mediante una solicitud HTTP el JSON retornado por el endpoint `/pet/findByStatus` y listar los nombres de las mascotas que se han vendido en formato de tupla {id, name}.

3. Crear una clase que requiera la estructura de datos de la tarea anterior y desarrollar un método que recorra la estructura para identificar cuántas mascotas tienen el mismo nombre y mostrar el resultado.

## Uso

1. Clone este repositorio en su máquina local.

```bash
git clone https://github.com/luisestebancastillo/resolucion-punto3-luiscastillo-inditex.git
```

## Uso

1. Abre el proyecto en tu IDE de preferencia.

2. Configura las dependencias Maven y asegúrate de tener todas las bibliotecas necesarias en tu proyecto.

3. Ejecuta la clase principal `Main` para llevar a cabo las tareas descritas en los objetivos.

4. Las pruebas unitarias se encuentran en el directorio `src/test/java` y pueden ser ejecutadas para verificar el funcionamiento del código.

## Requisitos
- Java 
- Maven

