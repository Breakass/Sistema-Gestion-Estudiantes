/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package SistemaGestionEstudiantes;

import java.util.List;
import java.util.Scanner;

/**
 * Programa principal: Esta clase solo se encarga de
 * leer datos del usuario, mostrarlos y manejar los errores.
 */
public class Main {

    public static void main(String[] args) {
        GestorEstudiantes gestor = new GestorEstudiantes();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1" -> registrarEstudiante(gestor, sc);
                case "2" -> listarEstudiantes(gestor);
                case "3" -> buscarEstudiante(gestor, sc);
                case "4" -> registrarCalificacion(gestor, sc);
                case "5" -> calcularPromedio(gestor, sc);
                case "6" -> {
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                }
                default -> System.out.println("Opcion invalida. Intenta de nuevo.");
            }
            System.out.println();
        }

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("========== SISTEMA DE GESTION DE ESTUDIANTES ==========");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Buscar estudiante por codigo");
        System.out.println("4. Registrar calificacion");
        System.out.println("5. Calcular promedio");
        System.out.println("6. Salir");
        System.out.print("Elige una opcion: ");
    }

    private static void registrarEstudiante(GestorEstudiantes gestor, Scanner sc) {
        try {
            System.out.print("Codigo del estudiante: ");
            String codigo = sc.nextLine();
            System.out.print("Nombre del estudiante: ");
            String nombre = sc.nextLine();

            Estudiante estudiante = gestor.registrarEstudiante(codigo, nombre);
            System.out.println("Estudiante registrado con exito: " + estudiante);

        } catch (EstudianteDuplicado | IllegalArgumentException e) {
            // Regla de negocio violada o dato de entrada vacio/invalido.
            System.out.println("No se pudo registrar: " + e.getMessage());
        }
    }

    private static void listarEstudiantes(GestorEstudiantes gestor) {
        List<Estudiante> estudiantes = gestor.listarEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("Aun no hay estudiantes registrados.");
            return;
        }
        System.out.println("Estudiantes registrados (" + estudiantes.size() + "):");
        for (Estudiante e : estudiantes) {
            System.out.println(" - " + e);
        }
    }

    private static void buscarEstudiante(GestorEstudiantes gestor, Scanner sc) {
        try {
            System.out.print("Codigo a buscar: ");
            String codigo = sc.nextLine();
            Estudiante estudiante = gestor.buscarEstudiante(codigo);
            System.out.println("Encontrado: " + estudiante);

        } catch (EstudianteNoEncontrado e) {
            System.out.println(e.getMessage());
        }
    }

    private static void registrarCalificacion(GestorEstudiantes gestor, Scanner sc) {
        try {
            System.out.print("Codigo del estudiante: ");
            String codigo = sc.nextLine();

            System.out.print("Calificacion (0 a 20): ");
            String textoNota = sc.nextLine();

            // Control de errores de ingreso de datos: el usuario podria
            // escribir texto en vez de un numero.
            double nota;
            try {
                nota = Double.parseDouble(textoNota.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Error: '" + textoNota + "' no es un numero valido.");
                return;
            }

            gestor.registrarCalificacion(codigo, nota);
            System.out.println("Calificacion registrada con exito.");

        } catch (EstudianteNoEncontrado | CalificacionInvalida e) {
            System.out.println("No se pudo registrar la calificacion: " + e.getMessage());
        }
    }

    private static void calcularPromedio(GestorEstudiantes gestor, Scanner sc) {
        try {
            System.out.print("Codigo del estudiante: ");
            String codigo = sc.nextLine();
            double promedio = gestor.calcularPromedio(codigo);
            System.out.printf("Promedio del estudiante %s: %.2f%n", codigo, promedio);

        } catch (EstudianteNoEncontrado e) {
            System.out.println(e.getMessage());
        }
    }
}