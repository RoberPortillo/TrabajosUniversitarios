package funciones.Inicializacion;

public class FactoriaInicializacion {

	public static Inicializacion getMetodo(String met) {
		switch(met) {
		case "Completa":
			return new InicializacionCompleta();
		case "Creciente":
			return new InicializacionCreciente();
		default:
			return new InicializacionCompleta();
		}
	}
}
