import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Medico implements ActionListener {
    
    JFrame frameM;
    JLabel lbl_codigo,lbl_cedula, lbl_nombre, lbl_apellido,lbl_dir,lbl_tel,lbl_espec,lbl_paciente_mes,lbl_paciente_anual;
    JTextField txt_codigo,txt_cedula, txt_nombre, txt_apellido,txt_dir,txt_tel,txt_paciente_mes,txt_paciente_anual;
    JButton btn_limpiar,btn_buscar,btn_agregar,btn_modificar,btn_eliminar,btn_listar;
    String codigo_espc,opcion,cedula_mod;
	DB db = new DB();
	Tabla_Medico tm = new Tabla_Medico();
	
    
    
    String[] especialidades = {"Medicina Interna", "Medicina Familiar", "Gineco Obstetricia", "Psiquiatria", "Cirugia General", "Pediatria", "Patologia", "Geriatria", "Dermatologia","Anestesiologia"};
    JComboBox<String> JBox_espc = new JComboBox<>(especialidades);
    
    	DefaultTableModel dtm_cliente;
 	JTable jt_cliente;
 	JScrollPane sp_cliente2;
    
    Medico (){
        
        frameM = new JFrame("Medico");
        frameM.setVisible(true);
        frameM.setLayout(null);
        frameM.setSize(800, 700);
        frameM.setMinimumSize(new Dimension(200, 200));
        frameM.setLocationRelativeTo(null);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frameP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lbl_codigo = new JLabel("Codigo:");
        lbl_codigo.setBounds(20, 20, 50, 20);
        frameM.add(lbl_codigo);
        
        txt_codigo = new JTextField();
        txt_codigo.setBounds(70, 20, 50, 20);
        frameM.add(txt_codigo);
        
        lbl_cedula = new JLabel("C?dula:");
        lbl_cedula.setBounds(20, 50, 50, 20);
        frameM.add(lbl_cedula);
        
        txt_cedula = new JTextField();
        txt_cedula.setBounds(70, 50, 50, 20);
        frameM.add(txt_cedula);
        
        lbl_nombre = new JLabel("Nombre:");
        lbl_nombre.setBounds(20, 80, 60, 20);
        frameM.add(lbl_nombre);
        
        txt_nombre = new JTextField();
        txt_nombre.setBounds(75, 80, 60, 20);
        frameM.add(txt_nombre);
        
        lbl_apellido = new JLabel("Apellido:");
        lbl_apellido.setBounds(20, 110, 80, 20);
        frameM.add(lbl_apellido);
        
        txt_apellido = new JTextField();
        txt_apellido.setBounds(77, 110, 80, 20);
        frameM.add(txt_apellido);
        
        lbl_dir = new JLabel("Direcci?n:");
        lbl_dir.setBounds(20, 140, 90, 20);
        frameM.add(lbl_dir);
        
        txt_dir = new JTextField();
        txt_dir.setBounds(83, 140, 90, 20);
        frameM.add(txt_dir);
        
        lbl_tel = new JLabel("Tel?fono:");
        lbl_tel.setBounds(20, 170, 80, 20);
        frameM.add(lbl_tel);
        
        txt_tel = new JTextField();
        txt_tel.setBounds(80, 170, 80, 20);
        frameM.add(txt_tel);
        
        lbl_espec = new JLabel("Especialidad:");
        lbl_espec.setBounds(20, 200, 120, 20);
        frameM.add(lbl_espec);
        
        
        JBox_espc.setBounds(110, 200, 140, 20);
        frameM.add(JBox_espc);
        
        lbl_paciente_mes = new JLabel("Pacientes Mensuales:");
        lbl_paciente_mes.setBounds(20, 230, 190, 20);
        frameM.add(lbl_paciente_mes);
        
        txt_paciente_mes = new JTextField();
        txt_paciente_mes.setBounds(150, 230, 40, 20);
        frameM.add(txt_paciente_mes);
        
        lbl_paciente_anual = new JLabel("Pacientes Anuales:");
        lbl_paciente_anual.setBounds(20, 260, 190, 20);
        frameM.add(lbl_paciente_anual);
        
        txt_paciente_anual = new JTextField();
        txt_paciente_anual.setBounds(140, 260, 40, 20);
        frameM.add(txt_paciente_anual);
        
        btn_limpiar = new JButton("Limpiar");
        btn_limpiar.setBounds(190, 20, 80, 20);
        btn_limpiar.addActionListener(this);
        frameM.add(btn_limpiar);
        
        btn_buscar = new JButton("Buscar");
        btn_buscar.setBounds(190,50,80,20);
        btn_buscar.addActionListener(this);
        frameM.add(btn_buscar);
        
        btn_agregar = new JButton("Agregar");
        btn_agregar.setBounds(190, 80, 80, 20);
        btn_agregar.addActionListener(this);
        frameM.add(btn_agregar);
        
        btn_modificar = new JButton("Modificar");
        btn_modificar.setBounds(190, 110, 80, 20);
        btn_modificar.addActionListener(this);
        frameM.add(btn_modificar);
        
        btn_eliminar = new JButton("Eliminar");
        btn_eliminar.setBounds(190, 140, 80, 20);
        btn_eliminar.addActionListener(this);
        frameM.add(btn_eliminar);
        
        btn_listar = new JButton("Listar");
        btn_listar.setBounds(190, 170, 80, 20);
        btn_listar.addActionListener(this);
        frameM.add(btn_listar);
        
        

	dtm_cliente = new DefaultTableModel();
        jt_cliente = new JTable(dtm_cliente);
        sp_cliente2 = new JScrollPane(jt_cliente);
        sp_cliente2.setBounds(50,300,500,200);
        frameM.add(sp_cliente2);

	btn_agregar.setEnabled(false);
	btn_modificar.setEnabled(false);
	btn_eliminar.setEnabled(false);

	txt_cedula.setEnabled(false);
	txt_nombre.setEnabled(false);
        txt_apellido.setEnabled(false);
        txt_dir.setEnabled(false);
        txt_tel.setEnabled(false);
        txt_paciente_mes.setEnabled(false);
        txt_paciente_anual.setEnabled(false);
	
	
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("entrando actionPerformed");
        if (e.getSource() == btn_listar)
            listar();
        if (e.getSource() == btn_agregar)
            agregar();
        if (e.getSource() == btn_buscar)
            buscar();
	 if (e.getSource() == btn_limpiar)
            limpiar();
         if (e.getSource() == btn_eliminar)
            eliminar();
          if (e.getSource() == btn_modificar)
            modificar();
           }

    public void listar()
    {
	
        
	tm.listar(dtm_cliente);        

        
        
    }

    public void agregar()
    {
opcion=JBox_espc.getSelectedItem().toString();
  if(opcion.equals("Medicina Interna")){
                codigo_espc="MI";
            }if(opcion.equals("Medicina Familiar")){
                codigo_espc="MF";
            }if(opcion.equals("Anestesiolog?a")){
                codigo_espc="AN";
            }if(opcion.equals("Gineco Obstetricia")){
                codigo_espc="GO";
            }if(opcion.equals("Psiquiatria")){
                codigo_espc="PS";
            }if(opcion.equals("Cirug?a General")){
                codigo_espc="CG";
            }if(opcion.equals("Pediatr?a")){
                codigo_espc="PD";
            }if(opcion.equals("Patolog?a")){
                codigo_espc="PA";
            }if(opcion.equals("Geriatr?a")){
                codigo_espc="GE";
            }if(opcion.equals("Dermatolog?a")){
                codigo_espc="DE";
            }

tm.setCodigo(txt_codigo.getText());
tm.setCedula(txt_cedula.getText());
tm.setNombre(txt_nombre.getText());
tm.setApellido(txt_apellido.getText());
tm.setDir(txt_dir.getText());
tm.setTel(txt_tel.getText());
tm.setPac_mes(txt_paciente_mes.getText());
tm.setPac_anual(txt_paciente_anual.getText());
tm.setCodigo_espc(codigo_espc);

txt_codigo.setText(null);
txt_cedula.setText(null);
txt_nombre.setText(null);
txt_apellido.setText(null);
txt_dir.setText(null);
txt_tel.setText(null);
txt_paciente_mes.setText(null);
txt_paciente_anual.setText(null);


tm.agregar();
JOptionPane.showMessageDialog(null,"Se Agrego el registro");
btn_buscar.setEnabled(true);
	
	
    }
        
    public void buscar()
    {
       
       		txt_codigo.setEnabled(false);
            	if (tm.buscar(txt_codigo.getText()))
            {
                btn_modificar.setEnabled(true);
		btn_eliminar.setEnabled(true);
		btn_buscar.setEnabled(false);
                btn_agregar.setEnabled(false);

		
		txt_cedula.setEnabled(true);
	    	txt_nombre.setEnabled(true);
            	txt_apellido.setEnabled(true);
            	txt_dir.setEnabled(true);
            	txt_tel.setEnabled(true);
           	txt_paciente_mes.setEnabled(true);
            	txt_paciente_anual.setEnabled(true);
	
		
		

            }else{
		btn_modificar.setEnabled(false);
		btn_eliminar.setEnabled(false);
		btn_buscar.setEnabled(false);
                btn_agregar.setEnabled(true);

		}
		
		txt_cedula.setText(tm.getCedula());
		txt_nombre.setText(tm.getNombre());
            	txt_apellido.setText(tm.getApellido());
            	txt_dir.setText(tm.getDir());
            	txt_tel.setText(tm.getTel());
            	txt_paciente_mes.setText(tm.getPac_mes());
            	txt_paciente_anual.setText(tm.getPac_anual());

		txt_cedula.setEnabled(true);
	    	txt_nombre.setEnabled(true);
            	txt_apellido.setEnabled(true);
            	txt_dir.setEnabled(true);
            	txt_tel.setEnabled(true);
           	txt_paciente_mes.setEnabled(true);
            	txt_paciente_anual.setEnabled(true);

        
    }
    
    public void eliminar()
    {
         
       tm.setCodigo(txt_codigo.getText());
	tm.eliminar();

	txt_cedula.setText(null);
	txt_nombre.setText(null);
	txt_apellido.setText(null);
	txt_dir.setText(null);
	txt_tel.setText(null);
	txt_paciente_mes.setText(null);
	txt_paciente_anual.setText(null);
	btn_buscar.setEnabled(true);
	
	JOptionPane.showMessageDialog(null,"Se Elimino el registro");
        
    }
    
    public void limpiar()
            
	{
		txt_codigo.setEnabled(true);
		txt_codigo.setText(null);
		txt_cedula.setText(null);
		txt_nombre.setText(null);
                txt_apellido.setText(null);
                txt_dir.setText(null);
                txt_tel.setText(null);
                txt_paciente_mes.setText(null);
                txt_paciente_anual.setText(null);
		btn_buscar.setEnabled(true);

			
	}
    
    public void modificar(){
        
        opcion=JBox_espc.getSelectedItem().toString();

   	    if(opcion.equals("Medicina Interna")){
                codigo_espc="MI";
            }if(opcion.equals("Medicina Familiar")){
                codigo_espc="MF";
            }if(opcion.equals("Anestesiologia")){
                codigo_espc="AN";
            }if(opcion.equals("Gineco Obstetricia")){
                codigo_espc="GO";
            }if(opcion.equals("Psiquiatr")){
                codigo_espc="PS";
            }if(opcion.equals("Cirugia General")){
                codigo_espc="CG";
            }if(opcion.equals("Pediatria")){
                codigo_espc="PD";
            }if(opcion.equals("Patologia")){
                codigo_espc="PA";
            }if(opcion.equals("Geriatria")){
                codigo_espc="GE";
            }if(opcion.equals("Dermatologia")){
                codigo_espc="DE";
            }


tm.setCodigo(txt_codigo.getText());
tm.setCedula(txt_cedula.getText());
tm.setNombre(txt_nombre.getText());
tm.setApellido(txt_apellido.getText());
tm.setDir(txt_dir.getText());
tm.setTel(txt_tel.getText());
tm.setPac_mes(txt_paciente_mes.getText());
tm.setPac_anual(txt_paciente_anual.getText());
tm.setCodigo_espc(codigo_espc); 

txt_codigo.setEnabled(true);
txt_codigo.setText(null);
txt_cedula.setText(null);
txt_nombre.setText(null);
txt_apellido.setText(null);
txt_dir.setText(null);
txt_tel.setText(null);
txt_paciente_mes.setText(null);
txt_paciente_anual.setText(null);
btn_buscar.setEnabled(true);   

tm.modificar();
JOptionPane.showMessageDialog(null,"Se Modifico el registro");
btn_modificar.setEnabled(false);
}


}