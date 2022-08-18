import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.engine.util.JRLoader;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Rp_Medico implements ActionListener
{

JFrame framerm;
JButton btn_reporte;
JRadioButton rb_nombre, rb_apellido,rb_esp;
ButtonGroup bg_orden;
DB db = new DB();

Rp_Medico()
{
	framerm = new JFrame("Reporte Paciente");
        framerm.setVisible(true);
        framerm.setLayout(null);
        framerm.setSize(100, 100);
        framerm.setMinimumSize(new Dimension(200, 200));
        framerm.setLocationRelativeTo(null);

	btn_reporte = new JButton("Reporte");
        btn_reporte.setBounds(20,20,100,20);
        btn_reporte.addActionListener(this);
        framerm.add(btn_reporte);

        rb_nombre = new JRadioButton("Ordenado por nombre");
        rb_nombre.setBounds(20,50,200,20);
        framerm.add(rb_nombre);

        rb_apellido = new JRadioButton("ordenado por apellido");
        rb_apellido.setBounds(20,70,200,20);
        framerm.add(rb_apellido);
	
	rb_esp = new JRadioButton("ordenado por especialidad");
        rb_esp .setBounds(20,90,200,20);
        framerm.add(rb_esp );

        bg_orden = new ButtonGroup();
        bg_orden.add(rb_nombre);
        bg_orden.add(rb_apellido);
	bg_orden.add(rb_esp);
}

public void actionPerformed(ActionEvent e)
{
        System.out.println("entrando actionPerformed");

        if (e.getSource() == btn_reporte)
           reporte();
}


	


		public void reporte()
    		{
		
        		try
        {            
            String orden;
            orden = "nombre";

            if (rb_nombre.isSelected())
               orden = "nombre";
            if (rb_apellido.isSelected())
               orden = "apellido";
	if (rb_esp.isSelected())
               orden = "especialidad";


            Map<String,Object> parametro = new HashMap<String,Object>();
            parametro.put("orden",orden);
            parametro.put("titulo","Listado de Clientes ordenado por "+orden);
            JasperPrint jasperPrint = JasperFillManager.fillReport("RM.jasper",parametro,db.con());        
            JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
            jasperViewer.setVisible(true);
            db.cerrar();
        }
        catch(Exception e)
        {
            System.out.println("error "+e.toString());
        }

   		 }
	
	
}
