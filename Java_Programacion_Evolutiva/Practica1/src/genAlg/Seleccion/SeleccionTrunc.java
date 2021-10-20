package genAlg.Seleccion;

import java.util.ArrayList;
import funciones.Individuo;
import java.util.Comparator;
import java.util.List;

public class SeleccionTrunc extends AlgoritmoSeleccion {

	//Proporcion de Trunc(Entre 0 y 1)
	private double trunc;
	private Comparator<Individuo> comp;
	
	public SeleccionTrunc(double trunc) {
		this.comp = new Comparator<Individuo>() {

			@Override
			public int compare(Individuo o1, Individuo o2) {
				if(o1.getAdap() >= o2.getAdap())
					return 1;
				else
					return -1;
			}
		};
		if(trunc > 1)
			this.trunc = 1;
		else if(trunc < 0)
			this.trunc = 0;
		else
			this.trunc = trunc;
	}
	@Override
	public List<Integer> seleccion(List<Individuo> poblacion) {
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
