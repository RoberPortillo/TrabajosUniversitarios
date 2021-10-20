package genAlg.Seleccion;

import java.util.ArrayList;
import funciones.Cromosoma;
import java.util.List;
import java.util.Random;


import javafx.util.Pair;

public class SeleccionRuleta extends AlgoritmoSeleccion{

	//Implementacion de seleccion por ruleta
	public List<Integer> seleccion(List<Cromosoma> poblacion, List<Pair<Double,Double>> puntuaciones) {
		Random rnd = new Random();
		double prob;
	
		List<Integer> seleccionados = new ArrayList<>();
		int pos;
		for(int i = 0; i < poblacion.size(); i++) {
			prob = rnd.nextDouble();
			pos = 0;
			 
			while((pos < poblacion.size()) && (prob > puntuaciones.get(pos).getValue()))
				pos++;
			seleccionados.add(pos);
			
		}
		
		return seleccionados;
	}
}

