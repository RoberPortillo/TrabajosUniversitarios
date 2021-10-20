package genAlg.Cruce;

public class FactoriaCruce {

	public static Cruce getCruce(String cruce){
		
		switch(cruce){
		case "Intercambio":
			return new CrucePorIntercambio();
			
		default:
			return new CrucePorIntercambio();
		
		}
	}
}
