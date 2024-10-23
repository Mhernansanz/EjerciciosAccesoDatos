/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ActividadesLibroej4;

import java.time.LocalDate;

/**
 *
 * @author tarde
 */
public class Proyectos {
    private String NOMBRE;
    private String DNI_NIF_JEFE_PROY;
    private LocalDate F_INICIO;
    private LocalDate F_FIN;

    public Proyectos(String NOMBRE, String DNI_NIF_JEFE_PROY, LocalDate F_INICIO) {
        this.NOMBRE = NOMBRE;
        this.DNI_NIF_JEFE_PROY = DNI_NIF_JEFE_PROY;
        this.F_INICIO = F_INICIO;
    }


    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getDNI_NIF_JEFE_PROY() {
        return DNI_NIF_JEFE_PROY;
    }

    public void setDNI_NIF_JEFE_PROY(String DNI_NIF_JEFE_PROY) {
        this.DNI_NIF_JEFE_PROY = DNI_NIF_JEFE_PROY;
    }

    public LocalDate getF_INICIO() {
        return F_INICIO;
    }

    public void setF_INICIO(LocalDate F_INICIO) {
        this.F_INICIO = F_INICIO;
    }

    public LocalDate getF_FIN() {
        return F_FIN;
    }

    public void setF_FIN(LocalDate F_FIN) {
        this.F_FIN = F_FIN;
    }
    
    
}
