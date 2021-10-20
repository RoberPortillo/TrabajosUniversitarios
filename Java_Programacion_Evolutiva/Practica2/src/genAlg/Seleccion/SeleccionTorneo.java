package genAlg.Seleccion;

import java.util.ArrayList;
import funciones.Cromosoma;
import java.util.List;
import java.util.Random;


import javafx.util.Pair;

public class SeleccionTorneo extends AlgoritmoSeleccion {
	
	@Override
	public List<Integer> seleccion(List<Cromosoma> poblacion, List<Pair<Double,Double>> puntuaciones) {
		

		Random rnd = new Random();
		List<Integer> seleccionados = new ArrayList<Integer>();
		//TODO fitness es int??
		double maxFit = 0, fit;int  pos; int maxPos = 0;
		for(int i = 0; i < poblacion.size(); i++) {
			//Nos quedamos con el mejor de los 3 elegidos al azar
			for(int j = 0; j < 3; j++) {
				pos = rnd.nextInt(poblacion.size());
				fit =  puntuaciones.get(pos).getKey(); //Casteo de double a int
				if(fit > maxFit) {
					maxFit = fit;
					maxPos = pos;
				}
			}
			seleccionados.add(maxPos);
		}
		return seleccionados;
	}

}