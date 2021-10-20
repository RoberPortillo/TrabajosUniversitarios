package genAlg.Seleccion;

import java.util.Comparator;

import funciones.Cromosoma;



public class FactoriaAlgSeleccion {
	
	public static AlgoritmoSeleccion getAlgSeleccion(String algoritmo,double trunc, Comparator<Cromosoma> comp) {
		
		switch(algoritmo) {
		case "Ruleta":
			return new SeleccionRuleta();
		case "Torneo":
			return new SeleccionTorneo();
		case "Estocastico":
			return new SeleccionEstocastica();
			
		case "Truncamiento":
			return new SeleccionTrunc(trunc, comp);
		case "Restos":
			return new SeleccionRestos();
		default:
			return new SeleccionRuleta();
		}
	}
}
