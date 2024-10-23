/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ActividadesLibroej4;

/**
 *
 * @author tarde
 */
public class Empleados {
    private String DNI_NIF;
    private String NOMBRE;

    public Empleados(String DNI_NIF, String NOMBRE) {
        this.DNI_NIF = DNI_NIF;
        this.NOMBRE = NOMBRE;
    }

    public String getDNI_NIF() {
        return DNI_NIF;
    }

    public void setDNI_NIF(String DNI_NIF) {
        this.DNI_NIF = DNI_NIF;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }
    
}
