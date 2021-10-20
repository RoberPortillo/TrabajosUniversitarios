package genAlg.Seleccion;

import java.util.ArrayList;
import java.util.List;


import javafx.util.Pair;
import funciones.Cromosoma;
public class SeleccionRestos extends AlgoritmoSeleccion{

	
	
	@Override
	public List<Integer> seleccion(List<Cromosoma> poblacion, List<Pair<Double,Double>> puntuaciones) {
		List<Integer> seleccionados = new ArrayList<Integer>();
		int i = 0;
		while(seleccionados.size() < poblacion.size() && i < poblacion.size()) {
			//Elegimos a cada i size * prob. de seleccion veces
			for(int j = 0; j < (poblacion.size() * puntuaciones.get(i).getKey()); j++) {
				seleccionados.add(i);
			}
			i++;
		}
		//Si no hemos seleccionados aun todos los necesarios
		if(seleccionados.size() < poblacion.size()) {
			List<Cromosoma> nuevaPob = new ArrayList<Cromosoma>();
			List<Integer> restosSeleccionados = new ArrayList<Integer>();
			//Creamos una poblacion auxiliar con los restantes
			for(int j = seleccionados.size(); j < poblacion.size(); j++) {
				nuevaPob.add(poblacion.get(j));
			}
			//Los añadimos por otro metodos (Ruleta en este caso)
			restosSeleccionados = FactoriaAlgSeleccion.getAlgSeleccion("ruleta", 0, null).seleccion(poblacion, puntuaciones);
			seleccionados.addAll(restosSeleccionados);
		}
		return seleccionados;
	}

	
}
