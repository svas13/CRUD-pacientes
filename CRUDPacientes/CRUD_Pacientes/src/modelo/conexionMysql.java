/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author INESMARIAO
 */
public class conexionMysql {
    Connection conectar=null; //variable conectar
    
    public Connection conexion(){  //método público conexion
        
        try{ //captura los errores
            Class.forName("com.mysql.cj.jdbc.Driver");  //dirección donde se encuentra nuestro controlador para hacer la conexión
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/bdcrudpaciente?useSSL=false&serverTimezone=America/Bogota","root",""); //inicializamos nuestra variable conectar
            //JOptionPane.showMessageDialog(null,"Conexión exitosa a la BD");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error de conexión a la BD " + e.getMessage());
        }
        return conectar; //retornamos la variable conectar
    }
    
}
