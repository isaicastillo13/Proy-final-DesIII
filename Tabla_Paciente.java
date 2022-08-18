import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class Tabla_Paciente
{

	private String cedula, nombre, apellido,dir,tel,prov,edad,sexo,codigo_prov,cedula_mod;
	private String sql;
	DB db = new DB();
	Tabla_Provincia tabla_prov = new Tabla_Provincia();

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

	   public String getCodProv() {
        return sexo;
    }

    public void setCodProv(String codigo_prov) {
        this.codigo_prov = codigo_prov;
    }

    public void inicializar(){
 	cedula = "";
        nombre = "";
        apellido = "";
        dir="";
        tel="";
        prov="";
        edad="";
        sexo="";
	codigo_prov="";
	}

public void listar(DefaultTableModel dtm)
{
	sql="";

	if(dtm.getRowCount()>0)
	dtm.setRowCount(0);
	else{

	dtm.addColumn("Cedula");
	dtm.addColumn("Nombre");
	dtm.addColumn("Apellido");
	dtm.addColumn("Direccion");
	dtm.addColumn("Telefono");
	dtm.addColumn("Provincia");
	dtm.addColumn("Edad");
	dtm.addColumn("Sexo");

	}


Object [] fila = new Object [8];
try
        {
            sql = "select * from paciente";
            ResultSet rs = db.executeQuery(sql);

            while (rs.next())
            {
                fila[0] = rs.getString("cedula");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido"); 
                fila[3] = rs.getString("direccion");
                fila[4] = rs.getString("telefono");
                prov = rs.getString("provincia");
		fila [5] = tabla_prov.buscar(prov);
                fila[6] = rs.getString("edad");
                fila[7] = rs.getString("sexo");
		
		dtm.addRow(fila);
                
            }
            	db.cerrar();
        }
        catch(Exception e)
        {
            System.out.println("error "+e.toString());
        }
}

public boolean buscar(String cedula){

boolean encontrado = false;
inicializar();
 try
        {
            
           
            sql = "select * from paciente where cedula='"+cedula+"'";
            System.out.println(sql);
            ResultSet rs = db.executeQuery(sql);
	    cedula = this.cedula;

            if (rs.next())
            {
                cedula = rs.getString("cedula");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido"); 
                dir=rs.getString("direccion");
                tel=rs.getString("telefono");
                prov=rs.getString("provincia");
                edad=rs.getString("edad");
                sexo=rs.getString("sexo");
	encontrado = true;
            }
		

           db.cerrar();
        }
        catch(Exception e)
        {
            System.out.println("error "+e.toString());
        }
return encontrado;


}

public void agregar()
    {
        sql = "";
        try
        {
           
            //sql = "insert into cliente(cedula, nombre, apellido) values ('9-1','Ricardo','Chan')";
            
            
            
            sql = "insert into paciente(cedula,nombre,apellido,direccion,telefono, provincia,edad,sexo) values ('";
            sql = sql + cedula +"','" + nombre +"','"+ apellido +"','"+ dir +"','";
            sql = sql + tel +"','"+codigo_prov+"','"+edad+"','"+ sexo +"')";
            System.out.println(sql);
            db.executeUpdate(sql);
  
           db.cerrar();
        }
        catch(Exception e)
        {
            System.out.println("error "+e.toString());
        }

    }
    
   public void modificar()
    {
       sql = "";
	cedula_mod = cedula;
        try
        {

            sql = "UPDATE paciente SET cedula='"+cedula+"'" + ",nombre='"+nombre+"'"+ ",apellido='"+apellido+"'"
                    + ",direccion='"+dir+"'"+ ",telefono='"+tel+"'"+ ",provincia='"+codigo_prov+"'"+ ",edad='"+edad+"'"
                    + ",sexo='"+sexo+"'"+"where cedula='"+cedula_mod+"'";
         
            System.out.println(sql);
            db.executeUpdate(sql);
            
                
    
            db.cerrar();
        }
        catch(Exception e)
        {
            System.out.println("error "+e.toString());
        }
    }

   public void eliminar()
    {
        sql = "";
        
        try
        {
            
            
            sql = "DELETE FROM paciente WHERE cedula='"+cedula+"'";
            System.out.println(sql);
            db.executeUpdate(sql);


        }
        catch(Exception e)
        {
            System.out.println("error "+e.toString());
        }
    


       } 
        
	
	
}