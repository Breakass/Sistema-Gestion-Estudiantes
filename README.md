# Sistema de Gestión de Estudiantes

Proyecto de Java (NetBeans, proyecto Ant) que permite registrar estudiantes, administrar sus calificaciones y calcular su promedio, aplicando reglas de negocio y manejo de errores.

## Descripción del problema

Una institución educativa necesita un sistema que permita a su personal administrativo registrar estudiantes y administrar sus calificaciones, garantizando que no existan códigos de estudiante duplicados, informando cuando un estudiante no existe, y controlando los errores de ingreso de datos.

## Objetos identificados

- **Estudiante** — el sujeto sobre el que se registra información y calificaciones.
- **Calificación** — un dato numérico asociado a un estudiante (atributo de `Estudiante`, no una clase propia).
- **GestorEstudiantes** — administra el conjunto completo de estudiantes: los registra, los busca, y aplica las reglas del negocio.

## Estructura del proyecto

```
src/SistemaGestionEstudiantes/
├── Estudiante.java                 # Entidad: código, nombre, calificaciones, promedio.
├── GestorEstudiantes.java          # Colección (Map<String, Estudiante>) y reglas de negocio.
├── EstudianteDuplicado.java        # Excepción: código de estudiante repetido.
├── EstudianteNoEncontrado.java     # Excepción: código de estudiante inexistente.
├── CalificacionInvalida.java       # Excepción: calificación fuera del rango 0-20.
└── Main.java                       # Menú de consola interactivo.
```

## Atributos de cada clase

| Clase | Atributo | Tipo | Descripción |
|---|---|---|---|
| Estudiante | codigo | String | Identificador único del estudiante. |
| Estudiante | nombre | String | Nombre completo del estudiante. |
| Estudiante | calificaciones | List\<Double\> | Historial de notas registradas (0 a 20). |
| GestorEstudiantes | estudiantes | Map\<String, Estudiante\> | Colección de todos los estudiantes, indexada por código. |
| GestorEstudiantes | NOTA_MINIMA / NOTA_MAXIMA | double (constantes) | Rango válido de una calificación: 0.0 a 20.0. |

## Responsabilidades y métodos

**Estudiante** — conoce y mantiene sus propios datos y calificaciones: `agregarCalificacion(double nota)`, `calcularPromedio()`, `getCodigo()` / `getNombre()` / `getCalificaciones()`.

**GestorEstudiantes** — administra la colección completa y hace cumplir las reglas de negocio: `registrarEstudiante(codigo, nombre)`, `listarEstudiantes()`, `buscarEstudiante(codigo)`, `registrarCalificacion(codigo, nota)`, `calcularPromedio(codigo)`, `existeCodigo(codigo)`.

## Reglas de negocio

- No se puede registrar un estudiante con un código que ya existe.
- No se puede registrar ni consultar una calificación de un estudiante que no existe.
- Una calificación debe estar entre 0 y 20; fuera de ese rango se rechaza.
- El código y el nombre de un estudiante no pueden estar vacíos.
- El promedio de un estudiante sin calificaciones registradas es 0.0 (estado válido, no un error).

## Colección elegida y justificación

Se usa `Map<String, Estudiante>` (`LinkedHashMap`) porque el código de estudiante ya es una clave única por definición del negocio, la búsqueda por código es la operación más frecuente del sistema (O(1) con Map, frente a O(n) con una lista), y `LinkedHashMap` además conserva el orden de registro para que "listar estudiantes" sea predecible. El historial de calificaciones de cada estudiante usa `List<Double>` porque las notas no son únicas ni se buscan por índice, solo se agregan en orden y se recorren para calcular el promedio.

## Errores potenciales y manejo

| Situación | Cómo se maneja |
|---|---|
| Código de estudiante duplicado | `EstudianteDuplicado` (excepción), capturada para mostrar un mensaje sin detener el programa. |
| Estudiante no encontrado | `EstudianteNoEncontrado`, lanzada al buscar/registrar nota/calcular promedio de un código inexistente. |
| Calificación fuera de rango | `CalificacionInvalida`, lanzada antes de guardar el dato. |
| Código o nombre vacío | `IllegalArgumentException` desde `GestorEstudiantes`. |
| Texto donde se espera un número | Se captura `NumberFormatException` al leer la calificación por teclado en `Main`. |

## Cómo ejecutarlo

Desde NetBeans: abrir el proyecto y ejecutar `Main.java`.

Desde la línea de comandos:

```bash
cd src
javac SistemaGestionEstudiantes/*.java
java SistemaGestionEstudiantes.Main
```

## Historial de este repositorio

El proyecto se subió organizado en 4 pull requests, cada uno con una parte funcional del sistema:

1. **Estructura del proyecto y clase Estudiante** — esqueleto del proyecto NetBeans y la entidad `Estudiante`.
2. **Excepciones y reglas de negocio** — las 3 excepciones y `GestorEstudiantes` con las validaciones.
3. **Menú principal** — `Main.java`, el punto de entrada interactivo.
4. **Documentación** — este README y el documento de análisis completo.
