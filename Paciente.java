import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;


public class Paciente implements ActionListener {
    
    JFrame frameP;
    JLabel lbl_cedula, lbl_nombre, lbl_apellido,lbl_dir,lbl_tel,lbl_prov,lbl_edad,lbl_sexo;
    JTextField txt_cedula, txt_nombre, txt_apellido,txt_dir,txt_tel,txt_edad,txt_sexo;
    JButton btn_limpiar,btn_buscar,btn_agregar,btn_modificar,btn_eliminar,btn_listar;
    String codigo_prov,opcion,cedula_mod;
    DB db = new DB();
    Tabla_Paciente tp = new Tabla_Paciente();
	

	
    
    String[] provincias = {"Bocas del Toro", "Coclé", "Colón", "Chiriquí", "Darién", "Herrera", "Los Santos", "Panamá", "Veraguas","Panamá Oeste"};
    JComboBox<String> JBox_prov = new JComboBox<>(provincias);
    
    String[] especialidades = {"Medicina Interna", "Medicina Familiar", "Gineco Obstetricia ", "Psiquiatría ", "Cirugía General", "Pediatría ", "Patología", "Geriatría", "Dermatología","Anestesiología"};
    JComboBox<String> JBox_espc = new JComboBox<>(especialidades);
    
    DefaultListModel<String> listModel;
    JList<String> lst_cliente;
    JScrollPane sp_cliente;

	DefaultTableModel dtm_cliente;
 	JTable jt_cliente;
 	JScrollPane sp_cliente2;
    
    Paciente (){
        
        frameP = new JFrame("Paciente");
        frameP.setVisible(true);
        frameP.setLayout(null);
        frameP.setSize(800, 700);
        frameP.setMinimumSize(new Dimension(200, 200));
        frameP.setLocationRelativeTo(null);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frameP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lbl_cedula = new JLabel("Cédula:");
        lbl_cedula.setBounds(20, 20, 50, 20);
        frameP.add(lbl_cedula);
        
        txt_cedula = new JTextField();
        txt_cedula.setBounds(70, 20, 50, 20);
        frameP.add(txt_cedula);
        
        lbl_nombre = new JLabel("Nombre:");
        lbl_nombre.setBounds(20, 50, 60, 20);
        frameP.add(lbl_nombre);
        
        txt_nombre = new JTextField();
        txt_nombre.setBounds(75, 50, 60, 20);
        frameP.add(txt_nombre);
        
        lbl_apellido = new JLabel("Apellido:");
        lbl_apellido.setBounds(20, 80, 80, 20);
        frameP.add(lbl_apellido);
        
        txt_apellido = new JTextField();
        txt_apellido.setBounds(77, 80, 80, 20);
        frameP.add(txt_apellido);
        
        lbl_dir = new JLabel("Dirección:");
        lbl_dir.setBounds(20, 110, 90, 20);
        frameP.add(lbl_dir);
        
        txt_dir = new JTextField();
        txt_dir.setBounds(83, 110, 90, 20);
        frameP.add(txt_dir);
        
        lbl_tel = new JLabel("Teléfono:");
        lbl_tel.setBounds(20, 140, 80, 20);
        frameP.add(lbl_tel);
        
        txt_tel = new JTextField();
        txt_tel.setBounds(80, 140, 80, 20);
        frameP.add(txt_tel);
        
        lbl_prov = new JLabel("Provincia:");
        lbl_prov.setBounds(20, 170, 100, 20);
        frameP.add(lbl_prov);
        
        
        JBox_prov.setBounds(83, 170, 100, 20);
        frameP.add(JBox_prov);
        
        lbl_edad = new JLabel("Edad:");
        lbl_edad.setBounds(20, 200, 40, 20);
        frameP.add(lbl_edad);
        
        txt_edad = new JTextField();
        txt_edad.setBounds(58, 200, 40, 20);
        frameP.add(txt_edad);
        
        lbl_sexo = new JLabel("Sexo:");
        lbl_sexo.setBounds(20, 230, 40, 20);
        frameP.add(lbl_sexo);
        
        txt_sexo = new JTextField();
        txt_sexo.setBounds(58, 230, 40, 20);
        frameP.add(txt_sexo);
        
        btn_limpiar = new JButton("Limpiar");
        btn_limpiar.setBounds(190, 20, 80, 20);
        btn_limpiar.addActionListener(this);
        frameP.add(btn_limpiar);
        
        btn_buscar = new JButton("Buscar");
        btn_buscar.setBounds(190,50,80,20);
        btn_buscar.addActionListener(this);
        frameP.add(btn_buscar);
        
        btn_agregar = new JButton("Agregar");
        btn_agregar.setBounds(190, 80, 80, 20);
        btn_agregar.addActionListener(this);
        frameP.add(btn_agregar);
        
        btn_modificar = new JButton("Modificar");
        btn_modificar.setBounds(190, 110, 80, 20);
        btn_modificar.addActionListener(this);
        frameP.add(btn_modificar);
        
        btn_eliminar = new JButton("Eliminar");
        btn_eliminar.setBounds(190, 140, 80, 20);
        btn_eliminar.addActionListener(this);
        frameP.add(btn_eliminar);
        
        btn_listar = new JButton("Listar");
        btn_listar.setBounds(190, 170, 80, 20);
        btn_listar.addActionListener(this);
        frameP.add(btn_listar);
        
        /*listModel = new DefaultListModel<String>();
        lst_cliente = new JList<String>(listModel);
        sp_cliente = new JScrollPane(lst_cliente);
        sp_cliente.setBounds(50,300,200,200);
        frameP.add(sp_cliente);*/

	dtm_cliente = new DefaultTableModel();
        jt_cliente = new JTable(dtm_cliente);
        sp_cliente2 = new JScrollPane(jt_cliente);
        sp_cliente2.setBounds(50,300,500,200);
        frameP.add(sp_cliente2);

	btn_agregar.setEnabled(false);
	btn_modificar.setEnabled(false);
	btn_eliminar.setEnabled(false);
	
	    txt_nombre.setEnabled(false);
            txt_apellido.setEnabled(false);
            txt_dir.setEnabled(false);
            txt_tel.setEnabled(false);
            txt_edad.setEnabled(false);
            txt_sexo.setEnabled(false);
	
	
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
	
        
	tp.listar(dtm_cliente);        

        
        
    }

    public void agregar()
    {
opcion=JBox_prov.getSelectedItem().toString();
   if(opcion.equals("Bocas del Toro")){
                codigo_prov="BO";
            }if(opcion.equals("Coclé")){
                codigo_prov="CL";
            }if(opcion.equals("Colón")){
                codigo_prov="CO";
            }if(opcion.equals("Chiriquí")){
                codigo_prov="CH";
            }if(opcion.equals("Darién")){
                codigo_prov="DA";
            }if(opcion.equals("Herrera")){
                codigo_prov="HE";
            }if(opcion.equals("Los Santos")){
                codigo_prov="LS";
            }if(opcion.equals("Panamá")){
                codigo_prov="PA";
            }if(opcion.equals("Veraguas")){
                codigo_prov="VE";
            }if(opcion.equals("Panamá Oeste")){
                codigo_prov="PO";
            }


tp.setCedula(txt_cedula.getText());
tp.setNombre(txt_nombre.getText());
tp.setApellido(txt_apellido.getText());
tp.setDir(txt_dir.getText());
tp.setTel(txt_tel.getText());
tp.setEdad(txt_edad.getText());
tp.setSexo(txt_sexo.getText());
tp.setCodProv(codigo_prov);

txt_cedula.setText(null);
txt_nombre.setText(null);
txt_apellido.setText(null);
txt_dir.setText(null);
txt_tel.setText(null);
txt_edad.setText(null);
txt_sexo.setText(null);

tp.agregar();

JOptionPane.showMessageDialog(null,"Se Agrego el registro");
btn_buscar.setEnabled(true);
	
	
    }
        
    public void buscar()
    {
       txt_cedula.setEnabled(false);
       
            if (tp.buscar(txt_cedula.getText()))
            {
                btn_modificar.setEnabled(true);
		btn_eliminar.setEnabled(true);
		btn_buscar.setEnabled(false);
                btn_agregar.setEnabled(false);

		txt_nombre.setEnabled(true);
            	txt_apellido.setEnabled(true);
            	txt_dir.setEnabled(true);
            	txt_tel.setEnabled(true);
            	txt_edad.setEnabled(true);
            	txt_sexo.setEnabled(true);
		

            }else{
		btn_modificar.setEnabled(false);
		btn_eliminar.setEnabled(false);
		btn_buscar.setEnabled(false);
                btn_agregar.setEnabled(true);
		}

		txt_nombre.setText(tp.getNombre());
            txt_apellido.setText(tp.getApellido());
            txt_dir.setText(tp.getDir());
            txt_tel.setText(tp.getTel());
            txt_edad.setText(tp.getEdad());
            txt_sexo.setText(tp.getSexo());
		
		txt_nombre.setEnabled(true);
            txt_apellido.setEnabled(true);
            txt_dir.setEnabled(true);
            txt_tel.setEnabled(true);
            txt_edad.setEnabled(true);
            txt_sexo.setEnabled(true);

   
        
    }
    
    public void eliminar()
    {
         
       	tp.setCedula(txt_cedula.getText());
	tp.eliminar();

	txt_cedula.setText(null);
	txt_nombre.setText(null);
	txt_apellido.setText(null);
	txt_dir.setText(null);
	txt_tel.setText(null);
	txt_edad.setText(null);
	txt_sexo.setText(null);
	btn_buscar.setEnabled(true);
	
	JOptionPane.showMessageDialog(null,"Se Elimino el registro");
        
    }
    
    public void limpiar()
            
	{
		txt_cedula.setEnabled(true);
		txt_cedula.setText(null);
		txt_nombre.setText(null);
                txt_apellido.setText(null);
                txt_dir.setText(null);
                txt_tel.setText(null);
                txt_edad.setText(null);
                txt_sexo.setText(null);
		btn_buscar.setEnabled(true);
		
		

			
	}
    
    public void modificar(){
        
        opcion=JBox_prov.getSelectedItem().toString();
   if(opcion.equals("Bocas del Toro")){
                codigo_prov="BO";
            }if(opcion.equals("Coclé")){
                codigo_prov="CL";
            }if(opcion.equals("Colón")){
                codigo_prov="CO";
            }if(opcion.equals("Chiriquí")){
                codigo_prov="CH";
            }if(opcion.equals("Darién")){
                codigo_prov="DA";
            }if(opcion.equals("Herrera")){
                codigo_prov="HE";
            }if(opcion.equals("Los Santos")){
                codigo_prov="LS";
            }if(opcion.equals("Panamá")){
                codigo_prov="PA";
            }if(opcion.equals("Veraguas")){
                codigo_prov="VE";
            }if(opcion.equals("Panamá Oeste")){
                codigo_prov="PO";
            }


tp.setCedula(txt_cedula.getText());
tp.setNombre(txt_nombre.getText());
tp.setApellido(txt_apellido.getText());
tp.setDir(txt_dir.getText());
tp.setTel(txt_tel.getText());
tp.setEdad(txt_edad.getText());
tp.setSexo(txt_sexo.getText());
tp.setCodProv(codigo_prov); 

txt_cedula.setText(null);
txt_nombre.setText(null);
txt_apellido.setText(null);
txt_dir.setText(null);
txt_tel.setText(null);
txt_edad.setText(null);
txt_sexo.setText(null);
btn_buscar.setEnabled(true);
   

tp.modificar();

JOptionPane.showMessageDialog(null,"Se Modifico el registro");
btn_modificar.setEnabled(false);
txt_cedula.setEnabled(true);
}


}