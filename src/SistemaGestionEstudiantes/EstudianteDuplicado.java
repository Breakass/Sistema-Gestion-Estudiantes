/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaGestionEstudiantes;

/**
 * Regla de negocio: "No se puede registrar un estudiante con un
 * código que ya existe". Se lanza cuando se intenta registrar un
 * código que el gestor ya tiene guardado.
 */
public class EstudianteDuplicado extends Exception {
    public EstudianteDuplicado(String codigo) {
        super("Ya existe un estudiante registrado con el codigo '" + codigo + "'.");
    }
}