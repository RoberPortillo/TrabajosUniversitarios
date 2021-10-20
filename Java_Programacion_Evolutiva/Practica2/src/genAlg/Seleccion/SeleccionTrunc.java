package genAlg.Seleccion;

import java.util.ArrayList;
import funciones.Cromosoma;
import java.util.Comparator;
import java.util.List;


import javafx.util.Pair;

public class SeleccionTrunc extends AlgoritmoSeleccion {

	//Proporcion de Trunc(Entre 0 y 1)
	private double trunc;
	private Comparator<Cromosoma> comp;
	
	public SeleccionTrunc(double trunc, Comparator<Cromosoma> comp) {
		this.comp = comp;
		if(trunc > 1)
			this.trunc = 1;
		else if(trunc < 0)
			this.trunc = 0;
		else
			this.trunc = trunc;
	}
	@Override
	public List<Integer> seleccion(List<Cromosoma> poblacion, List<Pair<Double,Double>> puntuaciones) {
		List<Integer> seleccionados = new ArrayList<Integer>();
		poblacion.sort(this.comp);
		//Buscamos entre los mejores de size/trunc
		for(int i = 0; i < (poblacion.size() * this.trunc); i++) {
			//Seleccionamos cada uno 1/trunc veces
			for(int j = 0; (j < (1 / this.trunc)) && (j < poblacion.size()); j++)
				seleccionados.add(i);
		}
		return seleccionados;
	}

	
}
