package funciones;

public enum Funcion {
	PROGN2("P2"), PROGN3("P3"), SIC("SIC");
	
	private String texto;
	
	Funcion(String txt){
		this.texto = txt;
	}
	
	@Override
	public String toString() {
		return this.texto;
	}
	
	public static Funcion selecionaFuncion() {
		int numero = (int) (Math.random() * 3) + 3;
		if(numero == 3) {
			return Funcion.SIC;
		}else if(numero == 4) {
			return Funcion.PROGN2;
		}else {
			return Funcion.PROGN3;
		}
	}
}
