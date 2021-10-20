package genAlg.Seleccion;

import java.util.List;



import funciones.Cromosoma;
import javafx.util.Pair;

public abstract class AlgoritmoSeleccion{
	
	//TODOS LOS METODOS DE SELECCION RECIBEN ESTOS PARAMETROS, SI NECESITAN MAS INFO SE LES DARA MEDIANTE SU CONSTRUCTORA
	public abstract List<Integer> seleccion(List<Cromosoma> poblacion,List<Pair<Double,Double>> puntuaciones);
}
