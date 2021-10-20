package genAlg.Mutacion;

public class FactoriaMutacion {

public static Mutacion getMutacion(String muta){
		
		switch(muta){
		
		case "Insercion":
			return new MutacionPorInsercion();
			
		case "Inversion":
			return new MutacionPorInsercion();
			
		case "Intercambio":
			return new MutacionPorIntercambio();
		
		case "Heuristica":
			return new MutacionHeurisica();
		case "Bloques":
			return new MutacionPorBloques();
			
		default:
			return new MutacionPorInsercion();
		
		
		}
		
	
	}
}
