package com.wordz;
import com.wordz.domain.Word;
import java.util.Scanner;
public class Wordz {
    public static void main(String[] args) {
        // No implementado
        System.out.println("Bienvenido a Wordz");
        System.out.println("Lista de palabras : ");
        System.out.print("Apple ");
        System.out.print("Bread ");
        System.out.print("Chair ");
        System.out.print("Dream ");
        System.out.print("Earth ");
        System.out.print("Flute ");
        System.out.print("Glass ");
        System.out.print("Heart ");
        System.out.print("Light ");
        System.out.print("Money ");
        System.out.print("Noble ");
        System.out.print("Peace ");
        System.out.println("\nAdivine la palabra");
        Scanner scanner = new Scanner(System.in);
        String palabra = new Word().getWord();
        //System.out.println(word.randomWord());
        // Implementando el contador de intentos 
        int intentos = 0;
        while (intentos < 6) {
            // Usuario inserta su palabra
            System.out.println("Intento " + (intentos + 1) + " de 6");
            String palabraUsuario = scanner.nextLine();
            
            // Se evalua la palabra

            if (palabraUsuario.equals(palabra)) {
                System.out.println("Ganaste");
                break;
            }
            
            else {
                // Como las palabras no coinciden, se evalua si la palabra contiene letras correctas
                // Se evalua si la palabra contiene letras parcialmente correctas

                // Si no tiene ninguna letra correcta, se muestra un mensaje de error
                
            }
            intentos++;
        }
        if (intentos == 6) {
            System.out.println("Perdiste");
            System.out.println("La palabra era: " + palabra);
        }
        scanner.close();
    }
}
