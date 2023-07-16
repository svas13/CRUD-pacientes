
package controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.paciente;
import modelo.pacienteDAO;
import vista.frmPacientes;
import modelo.enums.*;


/**
 *
 * @author INESMARIAO
 */
public class controladorPaciente implements ActionListener{
    //instanciamos las clases que necesitamos
    pacienteDAO dao=new pacienteDAO();
    paciente p=new paciente();
    frmPacientes vistaPaciente;
    DefaultTableModel modelo=new DefaultTableModel();
    
    //constructor de la clase
    public controladorPaciente(frmPacientes v){
        this.vistaPaciente=v;
        this.vistaPaciente.btnActualizarListaPac.addActionListener(this);        
        this.vistaPaciente.btnGuardarCrearPac.addActionListener(this);
        this.vistaPaciente.btnGuardarModificarPac.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==vistaPaciente.btnActualizarListaPac){
            listar(vistaPaciente.tblListarPacientes);
        }
        if(e.getSource()==vistaPaciente.btnGuardarCrearPac){
            crear();
            this.vistaPaciente.limpiarCamposfrmCrear();
        }
        if(e.getSource()==vistaPaciente.btnGuardarModificarPac){
            actualizar();
            this.vistaPaciente.limpiarCamposfrmModificar();
        }
    }
    
    //Método para crear pacientes
    public void crear(){
        paciente tmpPaciente = new paciente();
        
        TipoDocumento td = TipoDocumento.valueOf( vistaPaciente.cbxTipoDocCrearPac.getSelectedItem().toString()); 
        long numDoc = Long.parseLong(vistaPaciente.txtDocumentoCrearPac.getText());
        String nomb=vistaPaciente.txtNombresCrearPac.getText();
        String apell=vistaPaciente.txtApellidoCrearPac.getText();
        long tel = Long.parseLong(vistaPaciente.txtTelefonoCrearPac.getText());
        String dir=vistaPaciente.txtDireccionCrearPac.getText();
        String email=vistaPaciente.txtEmailCrearPac.getText();
        
        p.setTipoDoc(td);
        p.setNumeroDocumento(numDoc);
        p.setNombres(nomb);
        p.setApellidos(apell);
        p.setTelefono(tel);
        p.setDireccion(dir);
        p.setEmail(email);
        p.setEstado(true);
        if(dao.crearPaciente(p) == 1){
            JOptionPane.showMessageDialog(vistaPaciente, "Paciente creado con éxito");
        }else{
            JOptionPane.showMessageDialog(vistaPaciente, "Error en la creación del paciente");
        }
    }
    
    //Método para mostrar los datos en un JTable
    public void listar(JTable tabla){
        
        modelo=(DefaultTableModel)tabla.getModel();
        modelo.setRowCount(0);
        //crear una variable List del objeto paciente
        List<paciente>lista=dao.listar();
        //variable de tipo Objetc que va a ser un arreglo dentro va la cantidad de columnas que tiene nuestra tabla paciente
        Object[]object=new Object[9];
        for (int i=0; i<lista.size();i++){
            object[0]=lista.get(i).getId();
            object[1]=lista.get(i).getTipoDoc();
            object[2]=lista.get(i).getNumeroDocumento();
            object[3]=lista.get(i).getNombres();
            object[4]=lista.get(i).getApellidos();
            object[5]=lista.get(i).getTelefono();
            object[6]=lista.get(i).getDireccion();
            object[7]=lista.get(i).getEmail();
            object[8]=lista.get(i).isEstado();
            modelo.addRow(object);
        }
        vistaPaciente.tblListarPacientes.setModel(modelo);
    }
    public void actualizar(){
        try {
            paciente pac;
            
            int id = vistaPaciente.idTempo;
            long numDoc = Long.parseLong(vistaPaciente.txtDocumentoModificarPac.getText());
            TipoDocumento td = TipoDocumento.valueOf( vistaPaciente.cbxTipoDocModificarPac.getSelectedItem().toString()); 
            String nomb=vistaPaciente.txtNombresModificarPac.getText();
            String apell=vistaPaciente.txtApellidoModificarPac.getText();
            long tel = Long.parseLong(vistaPaciente.txtTelefonoModificarPac.getText());
            String dir=vistaPaciente.txtDireccionModificarPac.getText();
            String email=vistaPaciente.txtEmailModificarPac.getText();
            boolean estado = (vistaPaciente.cbxEstadoModificarPac.getSelectedItem() == "Activo") ? true : false;
            pac = new paciente(id, numDoc, td, nomb, apell, tel, dir, email, estado);
            
            if(dao.Modificar(pac) == 1){
                JOptionPane.showMessageDialog(vistaPaciente, "Paciente editado con éxito");
            }else{
                JOptionPane.showMessageDialog(vistaPaciente, "Error en la edicion del paciente");
            }
            vistaPaciente.cargarListaPacientes(vistaPaciente.cbxModificarBuscarPac);

        }catch (NumberFormatException ne) {
            JOptionPane.showMessageDialog(vistaPaciente, "Error al ingresar datos "+ne, "Datos No Validos", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(vistaPaciente, "Error General Editar"+e, "Datos No Validos ", JOptionPane.ERROR_MESSAGE);
        }
    }
    

}
