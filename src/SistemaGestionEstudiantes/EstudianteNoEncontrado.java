/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaGestionEstudiantes;

/**
 * Se lanza cuando se busca, registra una calificación o pide el
 * promedio de un código que no existe en el sistema. Cumple el
 * requisito "Informar cuando un estudiante no existe".
 */
public class EstudianteNoEncontrado extends Exception {
    public EstudianteNoEncontrado(String codigo) {
        super("No existe un estudiante registrado con el codigo '" + codigo + "'.");
    }
}