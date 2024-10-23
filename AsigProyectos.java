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
public class AsigProyectos {
    private String DNI_NIF_EMP;
    private int NUM_PROY;
    private LocalDate F_INICIO;
    private LocalDate F_FIN;

    public AsigProyectos(String DNI_NIF_EMP, int NUM_PROY, LocalDate F_INICIO) {
        this.DNI_NIF_EMP = DNI_NIF_EMP;
        this.NUM_PROY = NUM_PROY;
        this.F_INICIO = F_INICIO;
    }

    public String getDNI_NIF_EMP() {
        return DNI_NIF_EMP;
    }

    public void setDNI_NIF_EMP(String DNI_NIF_EMP) {
        this.DNI_NIF_EMP = DNI_NIF_EMP;
    }

    public int getNUM_PROY() {
        return NUM_PROY;
    }

    public void setNUM_PROY(int NUM_PROY) {
        this.NUM_PROY = NUM_PROY;
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
