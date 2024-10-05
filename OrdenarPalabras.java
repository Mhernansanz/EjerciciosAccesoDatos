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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author marco
 */
public class OrdenarPalabras {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Ha habido un error");
            System.exit(1);
        }
        
        String archivoEntrada = args[0];
        String archivoSalida = archivoEntrada + "_sort";
        
        try(BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
                BufferedWriter escribir = new BufferedWriter(new FileWriter(archivoSalida))) {
            
            List<String> palabras = new ArrayList<String>();
            String linea;
            
            while((linea = lector.readLine()) != null){
                palabras.add(linea);
            }
            Collections.sort(palabras);
            
            for (String palabra : palabras) {
                escribir.write(palabra);
                escribir.newLine();
            }
            
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo " + e.getMessage());
        }
    }
}
