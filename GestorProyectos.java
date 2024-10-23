/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ActividadesLibroej3;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author tarde
 */
public class GestorProyectos {

    private static Connection con = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver");
        }

        String URL = "jdbc:mysql://localhost:3306/GestorProyectos";
        String USER = "root";
        String PASS = "root";

        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("-------------------------------");
            menu(sc);
            System.out.println("-------------------------------");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean insertarEmpleados(Scanner sc) throws SQLException {
        boolean creado = false;        
        System.out.println("Introduzca el DNI del empleado(8 números y 1 letra): ");
        String dni = sc.nextLine();
        System.out.println("Introduzca el nombre del empleado: ");
        String nombre = sc.nextLine();
        String sql = "INSERT INTO empleados VALUES(?,?)";
        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, dni);
            pstm.setString(2, nombre);
            int a = pstm.executeUpdate();
            if (a == 1) {
                creado = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return creado;
    }

    public static void insertarProyectos(Scanner sc) throws SQLException {
        System.out.println("Introduzca el nombre del proyecto:");
        String nameProy = sc.nextLine();
        System.out.println("Intriduzca el DNI del jefe del proyecto(DNI del Empleado)");
        String dniJefeProy = sc.nextLine();
        System.out.println("Introduzca la fecha de inicio del proyecto(yyyy-mm-dd):");
        LocalDate fechaInicio = LocalDate.parse(sc.nextLine());
        System.out.println("Introduzca la fecha de fin del proyecto(yyyy-mm-dd):");
        LocalDate fechaFin = LocalDate.parse(sc.nextLine());
        String sql = "INSERT INTO proyectos(NOMBRE, DNI_NIF_JEFE_PROY, F_INICIO, F_FIN) VALUES(?,?,?,?)";
        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, nameProy);
            pstm.setString(2, dniJefeProy);
            if (fechaInicio.equals(null)) {
                pstm.setDate(3, Date.valueOf(LocalDate.now()));
            } else {
                pstm.setDate(3, Date.valueOf(fechaInicio));
            }
            if (fechaFin.equals(null)) {
                pstm.setDate(4, null);
            } else {
                pstm.setDate(4, Date.valueOf(fechaFin));
            }
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        consultaNumeroProyecto();
    }

    public static void asignarEmpAProyecto(Scanner sc) throws SQLException {
        System.out.println("Introduzca el DNI del empleado: ");
        String dniEmpleado = sc.nextLine();
        System.out.println("Introduzca el Numero del Proyecto: ");
        int numProy = Integer.parseInt(sc.nextLine());
        String sql = "INSERT INTO asig_proyectos VALUES(?,?,?,?)";
        LocalDate fecInicio, fecFin;
        fecInicio = consultaFechaInicioProyecto(numProy);
        fecFin = consultaFechaFinProyecto(numProy);
        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, dniEmpleado);
            pstm.setInt(2, numProy);
            pstm.setDate(3, Date.valueOf(fecInicio));
            pstm.setDate(4, Date.valueOf(fecFin));
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }

    public static LocalDate consultaFechaInicioProyecto(int numProy) throws SQLException {
        String sql = "SELECT F_INICIO FROM proyectos WHERE NUM_PROY = ?";
        LocalDate fecInicio = null;
        try (PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setInt(1, numProy);
            try (ResultSet rs = pstm.executeQuery()) {
                rs.next();
                fecInicio = rs.getDate(1).toLocalDate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fecInicio;
    }

    public static LocalDate consultaFechaFinProyecto(int numProy) throws SQLException {
        String sql = "SELECT F_FIN FROM proyectos WHERE NUM_PROY = ?";
        LocalDate fecFin = null;
        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, numProy);
            try (ResultSet rs = pstm.executeQuery()) {
                rs.next();
                fecFin = rs.getDate(1).toLocalDate();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return fecFin;
    }

    public static int consultaNumeroProyecto() throws SQLException {
        String sql = "SELECT NUM_PROY FROM proyectos";
        try (PreparedStatement pstm = con.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    public static void menu(Scanner sc) throws SQLException {
        String opcion;
        do {
            System.out.println("--------MENÚ--------");
            System.out.println("(1)-Insertar empleados");
            System.out.println("(2)-Insertar proyectos");
            System.out.println("(3)-Asignar empleado a un proyecto");
            System.out.println("Introduzca una opción(fin para terminar): ");
            opcion = sc.nextLine();
            if (opcion.equals("1")) {
                insertarEmpleados(sc);
            } else if (opcion.equals("2")) {
                insertarProyectos(sc);
            } else if (opcion.equals("3")) {
                asignarEmpAProyecto(sc);
            } else {
                System.out.println("Introduzca una opción correcta");
            }
        } while (!opcion.equalsIgnoreCase("fin"));
    }

}
