import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class Tabla_Especialidad
{
	private String codigo,descripcion;
	private String sql;
	DB db = new DB();
	
	
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

	public void inicializar(){
 	codigo = "";
       	}
	
	public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   

public String buscar(String codigo)
{
	
	try
        {
            
           
            sql = "select descripcion from especialidad where codigo='"+codigo+"'";
            System.out.println(sql);
            ResultSet rs = db.executeQuery(sql);
	    codigo= this.codigo;

            if (rs.next())
            {
                descripcion = rs.getString("descripcion");
		
                
		
            }
		

           db.cerrar();
        }
        catch(Exception e)
        {
            System.out.println("error "+e.toString());
        }
return descripcion;


	
}

	
	
}