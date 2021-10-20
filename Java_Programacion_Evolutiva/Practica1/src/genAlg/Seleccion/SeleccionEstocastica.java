package genAlg.Seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import funciones.Individuo;

public class SeleccionEstocastica extends AlgoritmoSeleccion{

	@Override
	public List<Integer> seleccion(List<Individuo> poblacion) {
		Random rnd = new Random();
		List<Integer> seleccionados = new ArrayList<Integer>();
		double size = (double) poblacion.size();
		double dist = 1 / size;
		double marca = 0.0 + (dist - 0.0) * rnd.nextDouble();
		
		int j = 0;
		for(int i = 0; i < poblacion.size(); i++) {
			//Buscar el primero cuya puntAcc es mayor que marca
			while(j < poblacion.size()) {
				
				if(poblacion.get(j).getPuntAcum() > marca) {
					seleccionados.add(j);
					marca += dist;
				}
				else
					j++;
			}
		}
		return seleccionados;
	}

	
}
