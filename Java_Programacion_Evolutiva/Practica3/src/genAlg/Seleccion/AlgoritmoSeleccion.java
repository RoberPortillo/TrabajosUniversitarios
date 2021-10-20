package genAlg.Seleccion;

import java.util.List;




import funciones.Individuo;

public abstract class AlgoritmoSeleccion{
	
	//TODOS LOS METODOS DE SELECCION RECIBEN ESTOS PARAMETROS, SI NECESITAN MAS INFO SE LES DARA MEDIANTE SU CONSTRUCTORA
	public abstract List<Integer> seleccion(List<Individuo> poblacion);
}
