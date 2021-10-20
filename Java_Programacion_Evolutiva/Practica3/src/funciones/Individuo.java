package funciones;

public class Individuo {
	
	private Arbol arbol; // estrategia de rastreo
	private int adaptación;// función de evaluación
	private double puntuacion;//puntuacion relativa:adaptación/sumadaptacion
	private double punt_acu; // puntuacion acumulada
	private boolean elite; // elitismo
	private String metIni;
	private int prof;
	
	public Individuo(int prof, String metIni) {
		this.prof = prof;
		this.arbol = new Arbol(this.prof);
		this.metIni = metIni;
		arbol.creaArbol(this.metIni);
	}
	
	//Constructor por copia
	private Individuo(Individuo src) {
		if(src != null) {
			this.adaptación = src.adaptación;
			this.puntuacion = src.puntuacion;
			this.punt_acu = src.punt_acu;
			this.elite = src.elite;
			this.metIni = src.metIni;
			this.prof = src.prof;
			this.arbol = src.arbol.clone();
		}
	}
	
	public Individuo clone() {
		return new Individuo(this);
	}
	
	public Arbol getArbol() {
		return this.arbol;
	}
	
	//SETTERS
	public void setAdap(int valor) {
		this.adaptación = valor;
		
		
	}
	
	public void setPunt(double valor) {
		this.puntuacion = valor;
	}
	
	public void setPuntAcum(double valor) {
		this.punt_acu = valor;
	}
	
	public void setElitismo(boolean valor) {
		this.elite = valor;
	}
	public void setArbol(Arbol valor) {
		this.arbol = valor;
	}
	
	//GETTERS
	public int getAdap() {
		return this.adaptación;
	}
	
	public double getPunt() {
		return this.puntuacion;
	}
	
	public double getPuntAcum() {
		return this.punt_acu;
	}
	
	public boolean getElitismo() {
		return this.elite;
	}
	
	public int getTam() {
		return this.arbol.getNumNodos();
	}
	
	public String getMetIni() {
		return this.metIni;
	}

	@Override
	public String toString() {
		return "[  adap: " + this.adaptación + ", punt: " + this.puntuacion + " , pAcc: " + this.punt_acu + "]";
	}
	
}
