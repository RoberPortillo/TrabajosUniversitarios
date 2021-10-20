package genAlg;

import java.util.*;

import org.math.plot.Plot2DPanel;

import funciones.*;
import genAlg.Cruce.FactoriaCruce;
import genAlg.Mutacion.FactoriaMutacion;
import genAlg.Mutacion.Mutacion;
import genAlg.Seleccion.FactoriaAlgSeleccion;

public class AlgoritmoGenetico {
	
	public List<Individuo> poblacion;
	private int maxGen;
	private int tamPob;
	private int maxProfundida;
	private double probCruce;
	private double probMut;
	private String modoSel;
	private String modoCruce;
	private String modoMut;
	private String modoIni;
	
	private boolean flagElit;
	private int tamElite;
	private Comparator<Individuo> compAdap;
	//public List<Tablero> tableros;
	Tablero mejorTablero;
	
	private int mejorGlobal;
	private int mejorGener;
	private int mediaGener;
	
	private Plot2DPanel graf;

	
	
	public AlgoritmoGenetico(int maxProfundidad, int tamPob, int maxGen, double probCruce, double probMut, String modoIni,
			String modoSel, String modoCruce, String modoMut, boolean flagElit, double pElit) {
		this.poblacion = new ArrayList<>();
		this.tamPob = tamPob;
		this.modoIni = modoIni;
		this.maxGen = maxGen;
		this.maxProfundida = maxProfundidad;
		this.probCruce = probCruce;
		this.probMut = probMut;
		this.modoSel = modoSel;
		this.modoCruce = modoCruce;
		this.modoMut = modoMut;
		this.mejorTablero = new Tablero();
		
		this.mejorGlobal = 0;
		this.mejorGener = 0;

		this.flagElit = flagElit;
		if(flagElit)
			this.tamElite = (int) (this.poblacion.size() * pElit);
		else
			this.tamElite = 0;
		
		this.compAdap = new Comparator<Individuo>() {
			
			@Override
			public int compare(Individuo o1, Individuo o2) {
				if(o1.getAdap() < o2.getAdap())
					return 1;
				else if(o1.getAdap() == o2.getAdap())
					return 0;
				else
					return -1;
			}
		};
		
		this.inicializaPoblacion();
	}
	
	//Inicializaremos por metodo completo, creciente o ramped and half
	private void inicializaPoblacion() {
		switch(this.modoIni) {
		case "RH":
			int nGrupos = this.maxProfundida - 1;
			int tamGrupo = this.tamPob / nGrupos;
			int profundidad = this.maxProfundida;
			for(int i = 0; i < nGrupos; i++){
				for(int j = 0; j < tamGrupo / 2; j++)
					this.poblacion.add(new Individuo(profundidad, "Completo"));
				for(int w = tamGrupo / 2; w < tamGrupo; w++)
					this.poblacion.add(new Individuo(profundidad, "Creciente"));
				profundidad--;
			}
		default:
			for(int i = 0; i < this.tamPob; i++) {
				this.poblacion.add(new Individuo(this.maxProfundida, this.modoIni));
			}
			break;
		}
		
	}
	
	public void genAlg() {
		
		int generacion = 0;
		List<Integer> seleccionados;
		List<Individuo> elite = null;
		Random r = new Random();
		List<Individuo> aux = new ArrayList<Individuo>();
		this.evalua();
		double[] mejorFitness = new double[this.maxGen];
		double[] mejorGeneracion = new double[this.maxGen];
		double[] mediaGeneracion = new double[this.maxGen];
		while (generacion < this.maxGen) {
			
			//ELITE
			if(flagElit)
				elite = this.separaMejores();
			
			// Seleccion
			seleccionados = FactoriaAlgSeleccion.getAlgSeleccion(this.modoSel, r.nextDouble())
					.seleccion(this.poblacion);
			//Generacion nueva se copia sobre la poblacion
			aux.clear();
			
			for (int i = 0; i < this.poblacion.size(); i++) {
				aux.add(this.poblacion.get(seleccionados.get(i)).clone());
			}
			this.poblacion = new ArrayList<>(aux);
			
			
			
			// CRUCE
			
			List<Integer> cruzar = this.reproducir();
			List<Individuo> aux1 = new ArrayList<Individuo>();
			for (int i = 0; i < cruzar.size() - 1; i = i + 2) {

				
				aux1 = FactoriaCruce.getCruce(this.modoCruce).cruce(this.poblacion.get(cruzar.get(i)), this.poblacion.get(cruzar.get(i+1)));
				this.poblacion.set(cruzar.get(i), aux1.get(0));
				this.poblacion.set(cruzar.get(i + 1), aux1.get(1));

			}
			// MUTACION
			
			Mutacion mut = FactoriaMutacion.getMutacion(this.modoMut);
			//TODO
			for(int i = 0; i < this.poblacion.size(); i++){
				this.poblacion.set(i, mut.muta(this.poblacion.get(i).clone(), this.probMut));
			}
			//INSERCION DE ELITE
			if(flagElit)
				this.insertaElite(elite);
			
			this.evalua();
			
			//Guardamos los valores de esta generacion
			mejorFitness[generacion] = this.mejorGlobal;
			mejorGeneracion[generacion] = this.mejorGener;
			mediaGeneracion[generacion] = this.mediaGener;
			
			generacion++;
			//if(generacion > 50)
				//System.out.println("wait");
		}
		
		//Dibujamos la grafica antes de salir
		this.pintaGrafica(mejorFitness, mejorGeneracion, mediaGeneracion);
	}
	
	//Devuelve solo los seleccionados
	public List<Integer> reproducir() {
		List<Integer> seleccionados = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < this.poblacion.size(); i++) {
			if (r.nextDouble() < this.probCruce) {
				seleccionados.add(i);
			}
		}
		if (seleccionados.size() % 2 == 1) {
			seleccionados.remove(seleccionados.size() - 1);
		}
		
		return seleccionados;
	}
	
	
	private void evalua() {
		int mediaTam = 0;
		double punt, puntAcc = 0, adapAcc = 0;
		Random rnd = new Random();
		//Calculamos la media de tamaño para poder hacer boating al calcular la adaptacion.
		for(int i = 0; i < this.poblacion.size(); i++)
			mediaTam += this.poblacion.get(i).getTam();
		mediaTam = mediaTam / this.poblacion.size();
		
		
		//Calculamos la adapatacion de cada individuo, en caso de que sea un individuo muy grande le asignamos una adaptacion baja para no premiar el tamaño
		List<Tablero> tableros = new ArrayList<Tablero>();
		for(int i = 0; i < this.poblacion.size(); i++) {
			
			if(this.poblacion.get(i).getTam() > mediaTam && rnd.nextDouble() < 0.1) {
				this.poblacion.get(i).setAdap(2);
			}
			else {
				Tablero tab = new Tablero();
				tab.initTablero();
				
				
				while(tab.getNumPasos() < 400 && tab.getContComida() < 89) { 
					tab.ejecutaArbol(this.poblacion.get(i).getArbol());
				}
				tableros.add(tab.clone());
				this.poblacion.get(i).setAdap(tableros.get(tableros.size()-1).getContComida());
				
			}
			adapAcc += this.poblacion.get(i).getAdap();
		}
		
		this.mejorGener = 0;
		//Calculamos la puntuacion(seleccion) de cada individuo y los valores de la generacion
		for(int i = 0; i < this.poblacion.size(); i++) {
			punt = this.poblacion.get(i).getAdap() / adapAcc;
			puntAcc += punt;
			this.poblacion.get(i).setPunt(punt);
			this.poblacion.get(i).setPuntAcum(puntAcc);
			
			this.mejorGener = Math.max(this.mejorGener, this.poblacion.get(i).getAdap());
			
			if(i < tableros.size() && this.mejorTablero.getContComida() < tableros.get(i).getContComida()) this.mejorTablero = tableros.get(i).clone();
		}
		
		this.mejorGlobal = Math.max(this.mejorGlobal, this.mejorGener);
		this.mediaGener = (int)adapAcc / this.poblacion.size();
	}
	
	//Devuelve una copia de los mejores individuos (elite)
		private List<Individuo> separaMejores() {
			//Ordeno la poblacion por fitness
			this.poblacion.sort(this.compAdap);
			List<Individuo> elite = new ArrayList<>();
			//Llenamos el array elite con copias de los mejores
			for(int i = 0; i < this.tamElite; i++) {
				elite.add(this.poblacion.get(i).clone());
			}
			return elite;
		}
	
		//Ordena la poblacion por fitness, y sustituye a los peores por la elite
		private void insertaElite(List<Individuo> elite){
			this.poblacion.sort(this.compAdap);
			for(int i = 0; i < this.tamElite; i++) {
				this.poblacion.set(this.poblacion.size()-1-i, elite.get(i));
			}
		}
		
		//Actualiza el estado de la grafica
		private void pintaGrafica(double[] mejorFitness,double[] mejorGeneracion,double[] mediaGeneracion) {
			
			Plot2DPanel plot = new Plot2DPanel();
			plot.addLegend("SOUTH");
			plot.addLinePlot("mejor fitnes",mejorFitness);
			plot.addLinePlot("mejor generacion",mejorGeneracion);
			plot.addLinePlot("media generacion",mediaGeneracion);
			
			this.graf = plot;	
		}
		
		public Plot2DPanel getGrafica() {
			return this.graf;
		}
		
		public int getMejorFitness() {
			return this.mejorTablero.getContComida();
		}
		public Tablero getMejorTablero() {
			return this.mejorTablero;
		}
}
