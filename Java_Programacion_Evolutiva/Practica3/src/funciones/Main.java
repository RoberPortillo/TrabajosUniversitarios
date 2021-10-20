package funciones;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import GUI.VentanaPrincipal;
import genAlg.AlgoritmoGenetico;
import genAlg.Tablero;
import genAlg.Mutacion.FactoriaMutacion;
import genAlg.Mutacion.Mutacion;

public class Main {

	public static void main(String[] args) {
		

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
	
	}/*
	Tablero tab = new Tablero();
		AlgoritmoGenetico ag = new AlgoritmoGenetico(10, 100, 100, 0.6, 0.2, "RH", "Ruleta", "Intercambio", "Funcional", false, 0.3);
		ag.genAlg();
	}*/
} 
