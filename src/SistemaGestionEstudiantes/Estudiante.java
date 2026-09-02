/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaGestionEstudiantes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa a un estudiante dentro del sistema.
 * Responsabilidad única: guardar sus propios datos y calificaciones,
 * y saber calcular su propio promedio. NO sabe nada de "los demás
 * estudiantes" ni de cómo se buscan o registran; eso es
 * responsabilidad de GestorEstudiantes.
 */


public class Estudiante {

    private final String codigo;
    private final String nombre;
    private final List<Double> calificaciones;

    public Estudiante(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.calificaciones = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    // Se expone como lista de solo lectura: nadie fuera de Estudiante
    // debería poder agregar una calificación sin pasar por
    // agregarCalificacion(), que es donde vive la lógica de esa acción.
    public List<Double> getCalificaciones() {
        return Collections.unmodifiableList(calificaciones);
    }

    public void agregarCalificacion(double nota) {
        calificaciones.add(nota);
    }

    public double calcularPromedio() {
        if (calificaciones.isEmpty()) {
            return 0.0;
        }
        double suma = 0.0;
        for (double nota : calificaciones) {
            suma += nota;
        }
        return suma / calificaciones.size();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %d calificacion(es) - Promedio: %.2f",
                codigo, nombre, calificaciones.size(), calcularPromedio());
    }
     
}