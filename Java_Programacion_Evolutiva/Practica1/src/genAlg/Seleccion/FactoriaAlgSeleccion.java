package genAlg.Seleccion;

public class FactoriaAlgSeleccion {
	
	public static AlgoritmoSeleccion getAlgSeleccion(String algoritmo,double trunc) {
		
		switch(algoritmo) {
		case "Ruleta":
			return new SeleccionRuleta();
		case "Torneo":
			return new SeleccionTorneo();
		case "Estocastico":
			return new SeleccionEstocastica();
			
		case "Truncamiento":
			return new SeleccionTrunc(trunc);
		default:
			return new SeleccionRuleta();
		}
	}
}
