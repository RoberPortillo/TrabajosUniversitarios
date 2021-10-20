package genAlg;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import GUI.VentanaPrincipal;
import funciones.Cromosoma;

public class Main {

	public static void main(String[] args) {
		/*
		List<Cromosoma> poblacion = new ArrayList<Cromosoma>();
		for(int i = 0; i <100;i++) {
			poblacion.add(new Cromosoma());
		}
		
		AlgoritmoGenetico alg = new AlgoritmoGenetico(poblacion, 100, 0.6, 0.05, "Ruleta", "OX", "Insercion", false,0.03);
		alg.geneticoAl();
		*/

	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
	
	
}
