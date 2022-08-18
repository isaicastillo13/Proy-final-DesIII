import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Menu implements ActionListener {

    JFrame frame;
    JMenuBar menubar;
    JMenu mn_inicio,mn_mant,mn_reporte;
    JMenuItem mn_presentacion, mn_salir,mni_paciente,mni_medico,rp_paciente,rp_medico;

    DefaultListModel<String> lstModel;
    JList<String> lst_lista;
    JScrollPane sp_lista;
	


    Menu() 
{

        frame = new JFrame("Base de Datos");
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(1000, 700);
        frame.setMinimumSize(new Dimension(200, 200));
        frame.setLocationRelativeTo(null);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menubar = new JMenuBar();

        mn_inicio = new JMenu("Inicio");

        mn_presentacion = new JMenuItem("Presentacion");
        mn_presentacion.addActionListener(this);
        mn_inicio.add(mn_presentacion);
        mn_inicio.addSeparator();

        mn_salir = new JMenuItem("Salir");
        mn_salir.addActionListener(this);
        mn_inicio.add(mn_salir);
        
        mn_mant = new JMenu("Mantenmiento");
        mni_paciente = new JMenuItem("Paciente");
        mni_paciente.addActionListener(this);
        mn_mant.add(mni_paciente);
        mn_mant.addSeparator();

        mni_medico = new JMenuItem("Medico");
        mni_medico.addActionListener(this);
        mn_mant.add(mni_medico);

	mn_reporte = new JMenu("Reportes");
        rp_paciente = new JMenuItem("Paciente");
        rp_paciente.addActionListener(this);
        mn_reporte.add(rp_paciente);
        mn_reporte.addSeparator();

	rp_medico = new JMenuItem("Medico");
        rp_medico.addActionListener(this);
        mn_reporte.add(rp_medico);
        
        

        menubar.add(mn_inicio);
        menubar.add(mn_mant);
	menubar.add(mn_reporte);
        frame.setJMenuBar(menubar);

}

    public void actionPerformed(ActionEvent e) 
{

        if (e.getSource() == mn_presentacion) {
            Presentacion.presentacion();
        }

        if (e.getSource() == mn_salir) {
           Salir.salir();
        }
        
        if (e.getSource() == mni_paciente) {
            new Paciente();
        }
	if (e.getSource() == mni_medico) {
            new Medico();
        }
	if (e.getSource() == rp_paciente) {
           new Rp_Paciente();
        }
	if (e.getSource() == rp_medico) {
            new Rp_Medico();
    	}

}
}
