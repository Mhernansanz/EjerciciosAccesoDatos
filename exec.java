/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ActividadesLibroej4;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author tarde
 */
public class exec {
    private static Connection con = null;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList <Empleados> empleados = new ArrayList<Empleados>();
        ArrayList <Proyectos> proyectos = new ArrayList<Proyectos>();
       
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            System.out.println("Error al cargar el driver");
        }
        
        String URL = "jdbc:mysql://localhost:3306/GestorProyectos";
        String USER = "root";
        String PASS = "root";
        
        try{
            con = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("------------------------------");
            menu(sc);
            System.out.println("------------------------------");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void menu(Scanner sc){
        String opcion;
        do {
            System.out.println("--------MENÚ--------");
            System.out.println("(1)-Insertar empleados");
            System.out.println("(2)-Insertar proyectos");
            System.out.println("(3)-Asignar empleado a un proyecto");
            System.out.println("Introduzca una opción(fin para terminar): ");
            opcion = sc.nextLine();
            if (opcion.equals("1")) {
                
            } else if (opcion.equals("2")) {

            } else if (opcion.equals("3")) {

            } else {
                System.out.println("Introduzca una opción correcta");
            }
        } while (!opcion.equalsIgnoreCase("fin"));
    }
    
    public static void save(ArrayList empleados){
        String sqlInsertarEmpleado = "INSERT INTO empleados VALUES(?,?)";
        try(PreparedStatement pstm = con.prepareStatement(sqlInsertarEmpleado)){
            pstm.setString(1, (String) empleados.get(0));
            pstm.setString(2, (String) empleados.get(1));
            pstm.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        String sqlInsertarProyecto = "INSERT INTO proyectos(NOMBRE, DNI_NIF_JEFE_PROY, F_INICIO) VALUES(?,?,?)";
        
    }
    
    public static boolean insertarEmpleados(Scanner sc, ArrayList empleados){
        boolean creado = false;      
        System.out.println("Introduzca el DNI del empleado(8 números y 1 letra): ");
        String dni = sc.nextLine();
        System.out.println("Introduzca el nombre del empleado: ");
        String nombre = sc.nextLine();
        Empleados e = new Empleados(dni, nombre);
        empleados.add(e);
        return creado;
    }
    
    public static void insertarProyectos(Scanner sc, ArrayList proyectos) {
        System.out.println("Introduzca el número del proyecto:");
        String nameProy = sc.nextLine();
        System.out.println("Intriduzca el DNI del jefe del proyecto(DNI del Empleado)");
        String dniJefeProy = sc.nextLine();
        System.out.println("Introduzca la fecha de inicio del proyecto(yyyy-mm-dd):");
        LocalDate fechaInicio = LocalDate.parse(sc.nextLine());
        Proyectos p = new Proyectos(nameProy, dniJefeProy, fechaInicio);
        proyectos.add(p);
    }
}
