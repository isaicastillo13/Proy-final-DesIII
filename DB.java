import java.sql.*;

public class DB
{

    String URL, user, pass;
    Connection con;
    Statement stmt;
    ResultSet rs;

    DB()
    {
        URL = "jdbc:mysql://127.0.0.1/proyectofinal?useSSL=false";
        user = "root";
        pass = "root";   
    }

    public void abrir()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL,user,pass);
            stmt = con.createStatement();
        }
        catch(Exception e)
        {
            System.out.println("error "+e.toString());
        }
    }

    public void cerrar()
    {
        try
        {
            rs.close();
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("error "+e.toString());
        }
    }

    public ResultSet executeQuery(String sql)
    {
        try
        {
            abrir();
            rs = stmt.executeQuery(sql);
        }
        catch(Exception e)
        {
            System.out.println("error "+e.toString());
        }
        return rs;
        
    }

    public void executeUpdate(String sql)
    {
        try
        {
            abrir();
            stmt.executeUpdate(sql);
            cerrar();
        }
        catch(Exception e)
        {
            System.out.println("error "+e.toString());
        }
    } 

	public Connection con()
    	{
        	abrir();
        	return con;    
    	}  
  
}