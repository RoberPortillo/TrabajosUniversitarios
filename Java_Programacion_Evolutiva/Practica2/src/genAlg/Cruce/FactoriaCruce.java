package genAlg.Cruce;

public class FactoriaCruce {

	public static Cruce getCruce(String cruce){
		
		switch(cruce){
		case "PMX" :
			return new CruceEmparejamientoParcial();
		
		case "OX" :
			return new CrucePorOrden();
		case "OXPrio":
			return new CrucePorOrdenConPosicionesPrioritarias();
		case "OXOrden":
			return new CrucePorOrdenConOrdenPrioritario();
		case "Ciclos":
			return new CruceCiclos();
			
		case "Ordinal":
			return new CruceMonopuntoOrdinal();
		case "Cruce propio":
			return new CruceImpar();
		case "Rutas":
			return new CrucePorRecombinacionDeRutas();
			
		default:
			return new CruceEmparejamientoParcial();
		
		}
	}
}
