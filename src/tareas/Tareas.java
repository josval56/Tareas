/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareas;

import Formularios.Apptask;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author Valmore
 */

public class Tareas {
    

    /**
     * @param args the command line arguments
     */

        
    Connection con;
    
    public Tareas(){
    
        try
    
        {
        
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios_db?serverTimezone=UTC","root","valmyBOCA2011");
            System.out.println("Se conecto a la base de datos");
    
        }
    
        catch(Exception e)
    
        {
        
            System.err.println("Error " + e);
    
        }
    }  
    public static void main(String[] args) {
        Statement st;
        ResultSet rs;
        PreparedStatement ps;
        Tareas mt = new Tareas();
        Scanner enter = new Scanner(System.in);       
        int s,condition,Task;
        // TODO code application logic here
        System.out.println("-- APP TASK --");
        System.out.println("-- MENU --");        
        System.out.println("1- Mostrar tarea");
        System.out.println("2- Ingresar tarea");
        System.out.println("3- Eliminar tarea");
        System.out.println("0 - Salir");
        System.out.println("Seleccionar:  ");
        s = enter.nextInt();
                
                       
    while(s != 0){
        switch(s){
            case 1:
               
                try 
                {
                    st = mt.con.createStatement();
                    rs = st.executeQuery("SELECT * FROM tarea");
                while(rs.next())
                    {
                       System.out.println(rs.getInt("idTarea") + " " + rs.getString("Nombre") + " " + rs.getString("Descripcion") + " " + rs.getInt("Estado"));
                    }
                    
                }         
                catch (Exception e)
                {
                    System.err.println("ERROR AL OBTENER LOS DATOS");
                } 
                System.out.println("-- APP TASK --");
                System.out.println("-- MENU --");        
                System.out.println("1- Mostrar tarea");
                System.out.println("2- Ingresar tarea");
                System.out.println("3- Eliminar tarea");
                System.out.println("0 - Salir");
                System.out.println("Seleccionar:  ");
                s = enter.nextInt();
            break;
            
            case 2:
                
                System.out.println("Elija nombre para la tarea:");
                String name;
                name = enter.next();
                System.out.println("Elija descripcion para la tarea:");
                String description;
                description = enter.next();
                System.out.println("Elija estado de la tarea 1 (activo) o cero (inactivo):");
                
                condition = enter.nextInt();
                while(condition != 0 && condition != 1){
                    System.out.println("Valor incorrecto. Vuelva a intentarlo...");
                    condition = enter.nextInt();
                }
                    
                try 
                
                {
            
                String query = "insert into tarea(Nombre,Descripcion,Estado) " + "values (?,?,?)";   
            
                ps = mt.con.prepareStatement(query);           
                ps.setString(1,name);
                ps.setString(2,description);
                ps.setInt(3,condition);
            
                ps.execute();
                
                 
          
                } 
        
                catch (Exception e)
                {
                System.err.println("ERROR AL OBTENER LOS DATOS");
                }
                System.out.println("-- APP TASK --");
                System.out.println("-- MENU --");        
                System.out.println("1- Mostrar tarea");
                System.out.println("2- Ingresar tarea");
                System.out.println("3- Eliminar tarea");
                System.out.println("0 - Salir");
                System.out.println("Seleccionar:  ");
                s = enter.nextInt();
                
            break;
            case 3:
                System.out.println("Elija id de la tarea a eliminar:");
                
                Task = enter.nextInt();
                
                    
                try 
                
                {
            
                String query = "delete from tarea where idTarea = ?;";   
            
                ps = mt.con.prepareStatement(query);           
                ps.setInt(1,Task);
                    
                int resultRowCount = ps.executeUpdate();
            
                if(resultRowCount > 0) {
                     System.out.println("La tarea se borro correctamente -> " + resultRowCount);
                }
                else    
                {
                    System.out.println("La tarea NO se borro o no se encuentra en los registros");
                }
          
                } 
        
                catch (Exception e)
                {
                System.err.println("ERROR AL OBTENER LOS DATOS");
                }
                System.out.println("-- APP TASK --");
                System.out.println("-- MENU --");        
                System.out.println("1- Mostrar tarea");
                System.out.println("2- Ingresar tarea");
                System.out.println("3- Eliminar tarea");
                System.out.println("0 - Salir");
                System.out.println("Seleccionar:  ");
                s = enter.nextInt();
            break;
            default:
                System.out.println("Valor no válido. Vuelva a intentarlo.");
                System.out.println("-- APP TASK --");
                System.out.println("-- MENU --");        
                System.out.println("1- Mostrar tarea");
                System.out.println("2- Ingresar tarea");
                System.out.println("3- Eliminar tarea");
                System.out.println("0 - Salir");
                System.out.println("Seleccionar:  ");
                s = enter.nextInt();
        }
        }
       
       System.out.println("Hasta luego...");
       
       System.exit(0);
                
    }
    
}        
