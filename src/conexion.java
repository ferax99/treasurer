/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class conexion {
    Connection conectar=null;
    Connection con = conexion();
    int datos_max = 250;
    boolean conectado = false;
    public Connection conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/Compras","root","binda99"); // loa: dspdfnl_4S
            this.conectado = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No estas conectado");
            System.out.print(e.getMessage());
        }
        return conectar;
    }
    boolean estadoConexion(){
        conexion();
    if (conectado == true){return true;}
    return false;
    }
    public String fechaHoy(){
    Date fecha = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    return formato.format(fecha);
    }
    public void setDatos_max(int datos_max) {
        this.datos_max = datos_max;
    }
    
    public String [] consulta(String instruccion,int indice){
    String [] datos = new String [datos_max];
        try{
        Statement ins = con.createStatement();
        ResultSet res = ins.executeQuery(instruccion);
        int h = 0;
        while(res.next()){
         
                datos[h] = res.getString(indice);
                h++;
                if(h == datos_max -1 ){break;}
            }
        }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "error en la consulta");
    }
    return datos;
    }
    public static void main(String[] args) {
        conexion con = new conexion();
        String a = con.consulta("describe Producto;",1)[0];
        System.out.println(a);
        System.out.println(con.fechaHoy());
    }
    
}
