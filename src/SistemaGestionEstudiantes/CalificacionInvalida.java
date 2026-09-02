/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaGestionEstudiantes;

/**
 * Se lanza cuando se intenta registrar una calificación fuera del
 * rango permitido (0 a 20). Cumple el requisito "Controlar errores
 * de ingreso de datos" a nivel de reglas de negocio.
 */
public class CalificacionInvalida extends Exception {
    public CalificacionInvalida(double notaIngresada, double minima, double maxima) {
        super(String.format(
                "La calificacion %.2f no es valida. Debe estar entre %.1f y %.1f.",
                notaIngresada, minima, maxima));
    }
}