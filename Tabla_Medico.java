import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class Tabla_Medico {

    private String codigo,cedula, nombre, apellido, dir, tel, especialidad, pac_mes, pac_anual, codigo_espc, codigo_mod;
    private String sql;
    DB db = new DB();
	Tabla_Especialidad tabla_espec = new Tabla_Especialidad();


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getPac_mes() {
        return pac_mes;
    }

    public void setPac_mes(String pac_mes) {
        this.pac_mes = pac_mes;
    }

    public String getPac_anual() {
        return pac_anual;
    }

    public void setPac_anual(String pac_anual) {
        this.pac_anual = pac_anual;
    }

    public String getCodigo_espc() {
        return codigo_espc;
    }

    public void setCodigo_espc(String codigo_espc) {
        this.codigo_espc = codigo_espc;
    }

    public String getCodigo_mod() {
        return codigo_mod;
    }

    public void setCodigo_mod(String codigo_mod) {
        this.codigo_mod = codigo_mod;
    }


    public void inicializar() {
        codigo = "";
        cedula = "";
        nombre = "";
        apellido = "";
        dir = "";
        tel = "";
        especialidad = "";
        pac_mes = "";
        pac_anual = "";
        codigo_espc = "";
    }

    
public void listar(DefaultTableModel dtm)
{
	sql="";

	if(dtm.getRowCount()>0)
	dtm.setRowCount(0);
	else{

	dtm.addColumn("Codigo");
	dtm.addColumn("Cedula");
	dtm.addColumn("Nombre");
	dtm.addColumn("Apellido");
	dtm.addColumn("Direccion");
	dtm.addColumn("Telefono");
	dtm.addColumn("Especialidad");
	dtm.addColumn("Paciente Mensual");
	dtm.addColumn("Paciente Anual");
	

	}


Object [] fila = new Object [9];
try
        {
           sql = "select * from medico";
            ResultSet rs = db.executeQuery(sql);

            while (rs.next())
            {
		fila[0] = rs.getString("codigo");
                fila[1] = rs.getString("cedula");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("apellido"); 
                fila[4] = rs.getString("direccion");
                fila[5] = rs.getString("telefono");
                especialidad = rs.getString("especialidad");
		System.out.print(especialidad);
		fila[6] = tabla_espec.buscar(especialidad);
                fila[7] = rs.getString("paciente_mes");
                fila[8] = rs.getString("paciente_anual");
		
		dtm.addRow(fila);
                
            }
            	db.cerrar();
        }
        catch(Exception e)
        {
            System.out.println("error "+e.toString());
        }
}

    public boolean buscar(String codigo) {

        boolean encontrado = false;
        inicializar();
	

        try {

            sql = "select * from medico where codigo='" + codigo + "'";
            System.out.println(sql);
            ResultSet rs = db.executeQuery(sql);
		codigo = this.codigo;
            

            if (rs.next()) {
                codigo = rs.getString("codigo");
                cedula = rs.getString("cedula");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                dir = rs.getString("direccion");
                tel = rs.getString("telefono");
                especialidad = rs.getString("especialidad");
                pac_mes = rs.getString("paciente_mes");
                pac_anual = rs.getString("paciente_anual");
                encontrado = true;
            }

            db.cerrar();
        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }
        return encontrado;

    }

    public void agregar() {
        sql = "";
        try {

            //sql = "insert into cliente(cedula, nombre, apellido) values ('9-1','Ricardo','Chan')";
            sql = "insert into medico(codigo,cedula,nombre,apellido,direccion,telefono,especialidad,paciente_mes,paciente_anual) values ('";
            sql = sql + codigo + "','" + cedula + "','" + nombre + "','" + apellido + "','" + dir + "','";
            sql = sql + tel + "','" + codigo_espc + "','" + pac_mes + "','" + pac_anual + "')";
            System.out.println(sql);
            db.executeUpdate(sql);

            db.cerrar();
        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }

    }

    public void modificar() {
        sql = "";
        codigo_mod = getCodigo();
        try {

            sql = "UPDATE medico SET cedula='" + cedula + "'" + ",nombre='" + nombre + "'" + ",apellido='" + apellido + "'"
                    + ",direccion='" + dir + "'" + ",telefono='" + tel + "'" + ",especialidad='" + codigo_espc + "'" + ",paciente_anual='" + pac_anual + "'"
                    + ",paciente_anual='" + pac_anual + "'" + " where codigo='" + codigo_mod + "'";

            System.out.println(sql);
            db.executeUpdate(sql);

            db.cerrar();
        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }
    }

    public void eliminar() {
        sql = "";
	codigo_mod = getCodigo();

        try {

            sql = "DELETE FROM medico WHERE codigo='" + codigo_mod + "'";
            System.out.println(sql);
            db.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }

    }

}

