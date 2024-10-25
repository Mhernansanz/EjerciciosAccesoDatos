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
        ArrayList<Empleados> empleados = new ArrayList<Empleados>();
        ArrayList<Proyectos> proyectos = new ArrayList<Proyectos>();
        ArrayList<AsigProyectos> asigProyectos = new ArrayList<AsigProyectos>();
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
            System.out.println("------------------------------");
            menu(sc, empleados, proyectos, asigProyectos);
            System.out.println("------------------------------");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void menu(Scanner sc, ArrayList<Empleados> empleados, ArrayList<Proyectos> proyectos, ArrayList<AsigProyectos> asigProyectos) throws SQLException {
        String opcion;
        do {
            System.out.println("--------MENÚ--------");
            System.out.println("(1)-Insertar empleados");
            System.out.println("(2)-Insertar proyectos");
            System.out.println("(3)-Asignar empleado a un proyecto");
            System.out.println("Introduzca una opción(fin para terminar): ");
            opcion = sc.nextLine();
            if (opcion.equals("1")) {
                insertarEmpleados(sc, empleados);
                save(empleados, proyectos, asigProyectos);
            } else if (opcion.equals("2")) {
                insertarProyectos(sc, proyectos);
                save(empleados, proyectos, asigProyectos);
            } else if (opcion.equals("3")) {
                asignarEmpAProyecto(sc, asigProyectos);
                save(empleados, proyectos, asigProyectos);
            } else {
                System.out.println("Introduzca una opción correcta");
            }
        } while (!opcion.equalsIgnoreCase("fin"));
    }

    public static void save(ArrayList<Empleados> empleados, ArrayList<Proyectos> proyectos, ArrayList<AsigProyectos> asigProyectos) {
        String sqlInsertarEmpleado = "INSERT INTO empleados VALUES(?,?)";
        for (Empleados empleado : empleados) {
            try (PreparedStatement pstm = con.prepareStatement(sqlInsertarEmpleado)) {
                pstm.setString(1, empleado.getDNI_NIF());
                pstm.setString(2, empleado.getNOMBRE());
                pstm.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        String sqlInsertarProyecto = "INSERT INTO proyectos VALUES(?,?,?,?,?)";
        for (Proyectos proyecto : proyectos) {
            try(PreparedStatement pstm = con.prepareStatement(sqlInsertarProyecto)){
                pstm.setInt(1, proyecto.getNUM_PROY());
                pstm.setString(2, proyecto.getNOMBRE());
                pstm.setString(3, proyecto.getDNI_NIF_JEFE_PROY());
                pstm.setDate(4, Date.valueOf(proyecto.getF_INICIO()));
                if (proyecto.getF_FIN() != null) {
                    pstm.setDate(5, Date.valueOf(proyecto.getF_FIN()));
                }else{
                    pstm.setDate(5, null);
                }
                pstm.executeUpdate();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        String sqlAsignarProyecto = "INSERT INTO asig_proyectos VALUES(?,?,?,?)";
        for (AsigProyectos asigProyecto : asigProyectos) {
            try (PreparedStatement pstm = con.prepareStatement(sqlAsignarProyecto)) {
                pstm.setString(1, asigProyecto.getDNI_NIF_EMP());
                pstm.setInt(2, asigProyecto.getNUM_PROY());
                pstm.setDate(3, Date.valueOf(asigProyecto.getF_INICIO()));
                if (asigProyecto.getF_FIN() != null) {
                    pstm.setDate(4, Date.valueOf(asigProyecto.getF_FIN()));
                }else{
                    pstm.setDate(4, null);
                }
                pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
        }

    }

    public static boolean insertarEmpleados(Scanner sc, ArrayList empleados) {
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
        System.out.println("Introduzca el número del proyecto(sino se pondrá automaticamente): ");
        int numProy = Integer.parseInt(sc.nextLine());
        System.out.println("Introduzca el nombre del proyecto:");
        String nameProy = sc.nextLine();
        System.out.println("Intriduzca el DNI del jefe del proyecto(DNI del Empleado)");
        String dniJefeProy = sc.nextLine();
        System.out.println("Introduzca la fecha de inicio del proyecto(yyyy-mm-dd):");
        LocalDate fechaInicio = LocalDate.parse(sc.nextLine());
        System.out.println("Introduzca la fecha de fin del proyecto(yyyy-mm-dd):");
        LocalDate fechaFin = LocalDate.parse(sc.nextLine());
       
        Proyectos p = new Proyectos(numProy, nameProy, dniJefeProy, fechaInicio, fechaFin);
        proyectos.add(p);
    }
    public static void asignarEmpAProyecto(Scanner sc, ArrayList asigProyectos) throws SQLException {
        System.out.println("Introduzca el DNI del empleado: ");
        String dniEmpleado = sc.nextLine();
        System.out.println("Introduzca el Numero del Proyecto: ");
        int numProy = Integer.parseInt(sc.nextLine());
        String sql = "INSERT INTO asig_proyectos VALUES(?,?,?,?)";
        LocalDate fecInicio, fecFin;
        fecInicio = consultaFechaInicioProyecto(numProy);
        fecFin = consultaFechaFinProyecto(numProy);
        AsigProyectos ap = new AsigProyectos(dniEmpleado, numProy, fecInicio, fecFin);
        asigProyectos.add(ap);
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
}
