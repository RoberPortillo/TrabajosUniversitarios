package genAlg.Mutacion;

public class FactoriaMutacion {

public static Mutacion getMutacion(String muta){
		
		switch(muta){
		
		case "Terminal":
			return new MutacionTerminal();
			
		case "Funcional":
			return new MutacionFuncional();
			
		case "Permutacion":
			return new MutacionPorPermutacion();
			
		case "Inicializacion":
			return new MutacionPorInicialización();
		default:
			return new MutacionTerminal();
		
		
		}
		
	
	}
}
