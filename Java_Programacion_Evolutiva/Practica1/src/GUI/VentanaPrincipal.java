package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import org.math.plot.Plot2DPanel;


import genAlg.AlgoritmoGenetico;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Plot2DPanel plot = new Plot2DPanel();

		// define the legend position
		plot.addLegend("SOUTH");
		
		JTabbedPane panelPest = new JTabbedPane();
		JPanel panel = new JPanel();
		panelPest.setBounds(324, 11, 460, 439);
		//panel.setBounds(324, 11, 460, 439);
		panel.add(plot);
	
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		panelPest.add("Grafica",panel);
		
		JPanel tablero = new JPanel();
		tablero.setLayout(new GridLayout(0, 1, 0, 0));
		panelPest.add("Tablero",tablero);
		contentPane.add(panelPest);
		
		JPanel panelFitness = new JPanel();
		panelFitness.setBounds(324, 470, 491, 80);
		panelFitness.setLayout(new GridLayout(5, 1, 0, 0));
		contentPane.add(panelFitness);
		//-----------------------------------------------------------------------------------------------------------------------
		
		
		//Seleccion
		JLabel lblSeleccion = new JLabel("Selección:");
		lblSeleccion.setBounds(10, 11, 56, 37);
		contentPane.add(lblSeleccion);
		
		JComboBox comboSeleccion = new JComboBox();
		comboSeleccion.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "Torneo", "Estocastico", "Truncamiento"}));
		comboSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		comboSeleccion.setBounds(133, 22, 150, 20);
		contentPane.add(comboSeleccion);
		
		
		//Cruce
		JLabel lblIni = new JLabel("Modo Población:");
		lblIni.setBounds(10, 80, 70, 20);
		contentPane.add(lblIni);
		
		JComboBox comboIni = new JComboBox();
		comboIni.setModel(new DefaultComboBoxModel(new String[] {"RH","Completa","Creciente"}));
		comboIni.setBounds(133, 80, 150, 20);
		contentPane.add(comboIni);		
		
		
		//Mutacion
		JLabel lblMutacion = new JLabel("Mutación:");
		lblMutacion.setBounds(10, 150, 60, 20);
		contentPane.add(lblMutacion);
		
		JComboBox comboMutacion= new JComboBox();
		comboMutacion.setModel(new DefaultComboBoxModel(new String[] {"Funcional", "Terminal","Permutacion","Inicializacion"}));
		comboMutacion.setBounds(133, 150, 150, 20);
		contentPane.add(comboMutacion);
		
		
		//Tamaño poblacion
		JLabel lblTamaoPoblacin = new JLabel("Tama\u00F1o poblaci\u00F3n:");
		lblTamaoPoblacin.setBounds(10, 220, 120, 20);
		contentPane.add(lblTamaoPoblacin);
		
		JSpinner tamPoblacion = new JSpinner();
		tamPoblacion.setModel(new SpinnerNumberModel(new Integer(100), null, null, new Integer(1)));
		tamPoblacion.setBounds(150, 220, 56, 20);
		contentPane.add(tamPoblacion);		
		
		
		
		//Numero Generaciones
		JLabel lblGeneraciones = new JLabel("Número Generaciones:");
		lblGeneraciones.setBounds(10, 290, 140, 20);
		contentPane.add(lblGeneraciones);
		
		JSpinner nGeneraciones = new JSpinner();
		nGeneraciones.setModel(new SpinnerNumberModel(100, 100, 1000, 50));
		nGeneraciones.setBounds(150, 290, 52, 20);
		contentPane.add(nGeneraciones);
		
		
		
		//Probabilidad de cruce
		JLabel lblProbabilidadCruce = new JLabel("Probabilidad cruce:");
		lblProbabilidadCruce.setBounds(10, 360, 120, 20);
		contentPane.add(lblProbabilidadCruce);
		
		JSpinner probCruce = new JSpinner();
		probCruce.setModel(new SpinnerNumberModel(0.6, 0.0, 1.0, 0.1));
		probCruce.setBounds(150, 360, 56, 20);
		contentPane.add(probCruce);
		
		
		//Probabilidad de mutacion
		JLabel lblProbabilidadMutacin = new JLabel("Probabilidad mutaci\u00F3n:");
		lblProbabilidadMutacin.setBounds(10, 420, 140, 20);
		contentPane.add(lblProbabilidadMutacin);
		
		JSpinner probMutacion = new JSpinner();
		probMutacion.setModel(new SpinnerNumberModel(0.3, 0.0, 1.0, 0.1));
		probMutacion.setBounds(150, 420, 56, 20);
		contentPane.add(probMutacion);
		
		//Profundidad
		JLabel lblProfundidad = new JLabel("Profundidad:");
		lblProfundidad.setBounds(10, 460, 140, 20);
		contentPane.add(lblProfundidad);
		
		JSpinner profundidad = new JSpinner();
		profundidad.setModel(new SpinnerNumberModel(4,3, 10, 1));
		profundidad.setBounds(150, 460, 56, 20);
		contentPane.add(profundidad);
		
		//Elitismo
		JRadioButton rdbtnElitismo = new JRadioButton("Elitismo");
		rdbtnElitismo.setBounds(10, 500, 70, 20);
		contentPane.add(rdbtnElitismo);
		
		JSpinner porElitismo = new JSpinner();
		porElitismo.setModel(new SpinnerNumberModel(0.03, 0.0, 1.0, 0.01));
		porElitismo.setBounds(90, 500, 52, 20);
		contentPane.add(porElitismo);

		
		JButton btnEjecutar = new JButton("EJECUTAR");
		btnEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AlgoritmoGenetico alg = new AlgoritmoGenetico((int) profundidad.getValue(), (int) tamPoblacion.getValue(),(int)tamPoblacion.getValue(),
						(Double) probCruce.getValue(), (Double) probMutacion.getValue(),"Intercambio",
						(String) comboIni.getSelectedItem(),(String) comboSeleccion.getSelectedItem(), 
						(String) comboMutacion.getSelectedItem(), rdbtnElitismo.isSelected(),
						(Double) porElitismo.getValue());
				alg.genAlg();
			
		
				
				JLabel mejorFitness = new JLabel("Mejor Fitness: " + alg.getMejorFitness());
				panelFitness.removeAll();
				panelFitness.add(mejorFitness);
				panelFitness.updateUI();
				panelFitness.repaint();
				
				repaint();
				
				JPanel t = alg.getMejorTablero().pintarTablero();
				tablero.removeAll();
				tablero.add(t);
				tablero.updateUI();
				tablero.repaint();
				repaint();
				
				Plot2DPanel plot = alg.getGrafica();
				panel.removeAll();
				panel.add(plot);
			
				panel.updateUI();
				panel.repaint();
				repaint();
			}
		});
		btnEjecutar.setBounds(152, 500, 97, 23);
		contentPane.add(btnEjecutar);
	}

}
