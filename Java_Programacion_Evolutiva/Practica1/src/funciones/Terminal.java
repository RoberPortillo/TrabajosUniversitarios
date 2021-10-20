package funciones;

public enum Terminal {
	AVANZA("A"), GIRA_DERECHA("GD"), GIRA_IZQUIERDA("GI");
	
	private String text;
	
	Terminal(String txt) {
		this.text = txt;
	}
	
	@Override
	public String toString() {
		return this.text;
	}
	
	public static Terminal seleccionaTerminal() {
		int numero = (int) (Math.random() * 3);
		if(numero == 0) {
			return Terminal.AVANZA;
		}else if(numero == 1) {
			return Terminal.GIRA_DERECHA;
		}else {
			return Terminal.GIRA_IZQUIERDA;
		}
	}
}
