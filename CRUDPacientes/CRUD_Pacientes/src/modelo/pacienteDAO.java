
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.enums.*;

/**
 *
 * @author INESMARIAO
 */


public class pacienteDAO {
    //Métodos para realizar las operaciones del CRUD
    
    //instanciar la clase conexión
    public static conexionMysql cc=new conexionMysql(); //instanciando la clase conexionMysql
    public static Connection con=cc.conexion();
    
    
    //Método Listar que retorna los datos del objeto
    public List listar(){
       List<paciente>datos=new ArrayList<>();  //variable de tipo List del Objeto paciente llamada datos
       String sql="select * from bdcrudpaciente.paciente";
       try{
           PreparedStatement ps=con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery(sql);

           while(rs.next()){
               paciente p=new paciente();
               p.setId(rs.getInt(1));
               TipoDocumento enumVal =  TipoDocumento.valueOf(rs.getString(2));
               p.setTipoDoc(enumVal);
               p.setNumeroDocumento(rs.getLong(3));
               p.setNombres(rs.getString(4));
               p.setApellidos(rs.getString(5));
               p.setTelefono(rs.getLong(6));
               p.setDireccion(rs.getString(7));
               p.setEmail(rs.getString(8));
               p.setEstado(rs.getBoolean(9));
               datos.add(p);
           }
       }catch (Exception e){
         JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
         e.printStackTrace();
       }
       return datos;
    }
    
 
    
    public int crearPaciente(paciente p){
        String sql="insert into bdcrudpaciente.paciente(tipoDocumento, numeroDocumento, nombres,apellidos,telefono,direccion,email,estado) values(?,?,?,?,?,?,?,?)";
       try{
           PreparedStatement ps=con.prepareStatement(sql);
           ps.setString(1, p.getTipoDoc().toString());
           ps.setLong(2, p.getNumeroDocumento());
           ps.setString(3, p.getNombres());
           ps.setString(4, p.getApellidos());
           ps.setLong(5, p.getTelefono());
           ps.setString(6, p.getDireccion());
           ps.setString(7, p.getEmail());
           ps.setBoolean(8, p.isEstado());
           ps.executeUpdate();
       }catch (Exception e){
         JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
         e.printStackTrace();
       }       
        return 1;
    }
    
    public int Modificar(paciente p){
        String sql="UPDATE bdcrudpaciente.paciente set tipoDocumento=?, numeroDocumento=?, nombres=?, apellidos=?, telefono=?, direccion=?, email=?, estado=? WHERE id=?";
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, p.getTipoDoc().toString());
            ps.setLong(2, p.getNumeroDocumento());
            ps.setString(3, p.getNombres());
            ps.setString(4, p.getApellidos());
            ps.setLong(5, p.getTelefono());
            ps.setString(6, p.getDireccion());
            ps.setString(7, p.getEmail());
            ps.setBoolean(8, p.isEstado());
            ps.setInt(9, p.getId());
            ps.executeUpdate();
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
         e.printStackTrace();            
        }
        return 1;
    }
    
    public ArrayList<paciente> Buscar (String Where){
        ArrayList<paciente> listPacientes = new ArrayList<paciente>();
        String sql=("SELECT * FROM bdcrudpaciente.paciente WHERE "+Where);
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery(sql);
            
            while (rs.next()){               
                paciente tmpPaciente = new paciente();
                tmpPaciente.id = rs.getInt("id");
                tmpPaciente.tipoDoc = TipoDocumento.valueOf(rs.getString("tipoDocumento"));
                tmpPaciente.numeroDocumento = rs.getLong("numeroDocumento");
                tmpPaciente.nombres = rs.getString("nombres");
                tmpPaciente.apellidos = rs.getString("apellidos");
                tmpPaciente.telefono = rs.getLong("telefono");
                tmpPaciente.direccion = rs.getString("direccion");
                tmpPaciente.email = rs.getString("email");
                tmpPaciente.estado = rs.getBoolean("estado");
                listPacientes.add(tmpPaciente);
            }
            //this.cn.cerrarConexion();
            //JOptionPane.showMessageDialog(null, "Se han encontrado resultados de Odontologos", "Buscar Odontologo", JOptionPane.INFORMATION_MESSAGE);
            return listPacientes;
        } catch (Exception e) {
            System.out.println("Error al Cargar los Datos: "+e.getMessage());
            return null;
        }
    }
        
        public static paciente getForId(int Id){
        try {
            String sql = ("SELECT * FROM bdcrudpaciente.paciente WHERE `id` = "+Id+";");
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery(sql);
            paciente tmpPaciente = new paciente();
            while (rs.next()){
                tmpPaciente.id = rs.getInt("id");
                tmpPaciente.tipoDoc = TipoDocumento.valueOf(rs.getString("tipoDocumento"));
                tmpPaciente.numeroDocumento = rs.getLong("numeroDocumento");
                tmpPaciente.nombres = rs.getString("nombres");
                tmpPaciente.apellidos = rs.getString("apellidos");
                tmpPaciente.telefono = rs.getLong("telefono");
                tmpPaciente.direccion = rs.getString("direccion");
                tmpPaciente.email = rs.getString("email");
                tmpPaciente.estado = rs.getBoolean("estado");
            }
            return tmpPaciente;
        } catch (Exception e) {
            return null;
        }
    }
    public paciente getForIdPaciente(int Id){
        
        try {
            String sql = ("SELECT * FROM bdcrudpaciente.paciente WHERE `id` = "+Id+";");
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery(sql);
            paciente tmpPaciente = new paciente();
            while (rs.next()){
                tmpPaciente.id = rs.getInt("id");
                tmpPaciente.tipoDoc = TipoDocumento.valueOf(rs.getString("tipoDocumento"));
                tmpPaciente.numeroDocumento = rs.getLong("numeroDocumento");
                tmpPaciente.nombres = rs.getString("nombres");
                tmpPaciente.apellidos = rs.getString("apellidos");
                tmpPaciente.telefono = rs.getLong("telefono");
                tmpPaciente.direccion = rs.getString("direccion");
                tmpPaciente.email = rs.getString("email");
                tmpPaciente.estado = rs.getBoolean("estado");
            }
            return tmpPaciente;
        } catch (Exception e) {
            return null;
        }
    }
    
    public String formarWherePaciente (String Campo, String Valor){
        String Where = "1";       
        if (Campo.equalsIgnoreCase("Nombres")){
            Where = "nombres LIKE '%"+Valor+"%'";
        }else if (Campo.equalsIgnoreCase("Apellidos")){
            Where = "apellidos LIKE '%"+Valor+"%'";
        }else if (Campo.equalsIgnoreCase("Telefono")){
            Where = "telefono = "+Valor+"";
        }else if (Campo.equalsIgnoreCase("No. documento")){
            Where = "numeroDocumento = "+Valor+"";
        }
        return Where;
    }
    
    public void listPacientesTabla (ArrayList<paciente> arrPacientes, JTable tabla){
        DefaultTableModel modelBase = (DefaultTableModel) tabla.getModel();
        modelBase.setRowCount(0);
        for (paciente pacie : arrPacientes) {
            Vector tempPaciente = new Vector();
            tempPaciente.add(pacie.getId());
            tempPaciente.add(pacie.getTipoDoc());
            tempPaciente.add(pacie.getNumeroDocumento());
            tempPaciente.add(pacie.getNombres());
            tempPaciente.add(pacie.getApellidos());
            tempPaciente.add(pacie.getTelefono());
            tempPaciente.add(pacie.getDireccion());
            tempPaciente.add(pacie.getEmail());
            tempPaciente.add(pacie.isEstado());
            modelBase.addRow(tempPaciente);
        }
        tabla.setModel(modelBase);
    }
    
}
