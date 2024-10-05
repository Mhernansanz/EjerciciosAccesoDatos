/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1mezclaFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author marco
 */
public class MezclaFicheros {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Ha habido un error");
            System.exit(1);
        }
        
        String archivo1 = args[0];
        String archivo2 = args[1];
        String archivoSalida = args[2];
        
        try(BufferedReader lector1 = new BufferedReader(new FileReader(archivo1));
                BufferedReader lector2 = new BufferedReader(new FileReader(archivo2));
                BufferedWriter escribir = new BufferedWriter(new FileWriter(archivoSalida))) {
            
            String linea1 = "";
            String linea2 = "";
            while((linea1 = lector1.readLine()) != null || (linea2 = lector2.readLine()) != null){
                if (linea1 != null) {
                    escribir.write(linea1);
                    escribir.newLine();
                }
                if (linea2 != null) {
                    escribir.write(linea2);
                    escribir.newLine();
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error al procesar los archivos " + e.getMessage());
        }
    }
}
