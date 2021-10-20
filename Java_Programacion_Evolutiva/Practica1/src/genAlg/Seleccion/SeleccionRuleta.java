package genAlg.Seleccion;

import java.util.ArrayList;
import funciones.Individuo;
import java.util.List;
import java.util.Random;

public class SeleccionRuleta extends AlgoritmoSeleccion{

	//Implementacion de seleccion por ruleta
	public List<Integer> seleccion(List<Individuo> poblacion) {
		Random rnd = new Random();
		double prob;
	
		List<Integer> seleccionados = new ArrayList<>();
		int pos;
		for(int i = 0; i < poblacion.size(); i++) {
			prob = rnd.nextDouble();
			pos = 0;
			 
			while((pos < poblacion.size()) && (prob > poblacion.get(pos).getPuntAcum()))
				pos++;
			seleccionados.add(pos);
			
		}
		
		return seleccionados;
	}
}

