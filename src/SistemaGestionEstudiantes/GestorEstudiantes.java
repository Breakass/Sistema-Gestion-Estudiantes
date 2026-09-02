/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaGestionEstudiantes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Administra la colección de estudiantes del sistema.
 * Responsabilidad: registrar, listar, buscar, registrar
 * calificaciones y calcular promedios, aplicando las reglas de
 * negocio (código único, existencia del estudiante, rango de notas).
 *
 * Colección elegida: Map<String, Estudiante> (LinkedHashMap).
 * - El código de estudiante es único -> un Map modela eso de forma
 *   natural: una clave = un estudiante, y map.containsKey(codigo)
 *   resuelve "evitar códigos duplicados" en O(1).
 * - "Buscar un estudiante por código" es la operación más frecuente
 *   del sistema -> con un Map es una búsqueda directa O(1) en
 *   promedio; con una List habría que recorrerla entera (O(n)).
 * - Se usa LinkedHashMap (no HashMap) para además conservar el
 *   ORDEN en que se registraron los estudiantes, útil para
 *   "listar estudiantes" de forma predecible.
 */
public class GestorEstudiantes {

    public static final double NOTA_MINIMA = 0.0;
    public static final double NOTA_MAXIMA = 20.0;

    private final Map<String, Estudiante> estudiantes = new LinkedHashMap<>();

    public Estudiante registrarEstudiante(String codigo, String nombre)
            throws EstudianteDuplicado {

        validarTexto(codigo, "El codigo");
        validarTexto(nombre, "El nombre");

        String codigoNormalizado = codigo.trim();
        if (estudiantes.containsKey(codigoNormalizado)) {
            throw new EstudianteDuplicado(codigoNormalizado);
        }

        Estudiante nuevo = new Estudiante(codigoNormalizado, nombre.trim());
        estudiantes.put(codigoNormalizado, nuevo);
        return nuevo;
    }

    public List<Estudiante> listarEstudiantes() {
        return Collections.unmodifiableList(new ArrayList<>(estudiantes.values()));
    }

    public Estudiante buscarEstudiante(String codigo) throws EstudianteNoEncontrado {
        Estudiante estudiante = estudiantes.get(codigo == null ? null : codigo.trim());
        if (estudiante == null) {
            throw new EstudianteNoEncontrado(codigo);
        }
        return estudiante;
    }

    public void registrarCalificacion(String codigo, double nota)
            throws EstudianteNoEncontrado, CalificacionInvalida {

        Estudiante estudiante = buscarEstudiante(codigo);

        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new CalificacionInvalida(nota, NOTA_MINIMA, NOTA_MAXIMA);
        }

        estudiante.agregarCalificacion(nota);
    }

    public double calcularPromedio(String codigo) throws EstudianteNoEncontrado {
        return buscarEstudiante(codigo).calcularPromedio();
    }

    public boolean existeCodigo(String codigo) {
        
        String existeCodigo = codigo == null ? null : codigo.trim();
        return estudiantes.containsKey(existeCodigo);
    }

    public int totalEstudiantes() {
        return estudiantes.size();
    }

    private void validarTexto(String valor, String etiqueta) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(etiqueta + " no puede estar vacio.");
        }
    }
}