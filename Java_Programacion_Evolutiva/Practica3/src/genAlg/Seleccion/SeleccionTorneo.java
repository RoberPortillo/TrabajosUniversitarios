package genAlg.Seleccion;

import java.util.ArrayList;
import funciones.Individuo;
import java.util.List;
import java.util.Random;

public class SeleccionTorneo extends AlgoritmoSeleccion {
	
	@Override
	public List<Integer> seleccion(List<Individuo> poblacion) {
		

		Random rnd = new Random();
		List<Integer> seleccionados = new ArrayList<Integer>();
	
		double maxFit = 0, fit; int pos, maxPos = 0;
		for(int i = 0; i < poblacion.size(); i++) {
			//Nos quedamos con el mejor de los 3 elegidos al azar
			for(int j = 0; j < 3; j++) {
				pos = rnd.nextInt(poblacion.size());
				fit =  poblacion.get(pos).getAdap(); //Casteo de double a int
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