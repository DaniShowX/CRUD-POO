package sql;

import getset.variables;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class crudsql extends conexionsql{
    java.sql.Statement st;
    ResultSet rs;
    variables var=new variables(); 
    
    public void insertar(String nombre, String apellido, String puesto){
        try{
            Connection conexion= conectar();
            st=conexion.createStatement();
            String sql="insert into empleados(nombre,apellido,puesto) values('"+nombre+"','"+apellido+"','"+puesto+"');";
            st.execute(sql);
            st.close();
            conexion.close();
            JOptionPane.showMessageDialog(null, "El registro se guardo correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "El registro no se guardo "+e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void mostrar(String idempleado){
        try{
            Connection conexion=conectar();
            st =conexion.createStatement();
            String sql="select * from empleados where idempleado='"+idempleado+"';";
            rs=st.executeQuery(sql);
            if(rs.next()){
                var.setIdempleado(rs.getString("idempleado"));
                var.setNombre(rs.getString("nombre"));
                var.setApellido(rs.getString("apellido"));
                var.setPuesto(rs.getString("puesto"));
            } else{
                var.setIdempleado("");
                var.setNombre("");
                var.setApellido("");
                var.setPuesto("");
                JOptionPane.showMessageDialog(null, "No se encontro registro", "Sin registro", JOptionPane.INFORMATION_MESSAGE);
            }
            st.close();
            conexion.close();
            
        }catch (Exception e){
             JOptionPane.showMessageDialog(null, "Error del sistema de busqueda", "Error busqueda", JOptionPane.ERROR_MESSAGE);
        }
    }
}
