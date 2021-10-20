package genAlg;

import java.util.*;

import org.math.plot.*;

import funciones.Cromosoma;
import genAlg.Cruce.FactoriaCruce;
import genAlg.Mutacion.FactoriaMutacion;
import genAlg.Mutacion.Mutacion;
import genAlg.Seleccion.FactoriaAlgSeleccion;
import genAlg.util.CompFitness;
import javafx.util.Pair;

public class AlgoritmoGenetico {
	

	private List<Cromosoma> poblacion;
	private Cromosoma mejorCromosoma;
	private int maxGen;
	private double probCruce;
	private double probMut;
	private String modoSel;
	private String modoCruce;
	private String modoMut;
	// Valores de la poblacion (fitness adapatativo)

	private double mejorGlobal;
	private double mejorGener;
	private double mediaGener;
	//Valores de la población (fitness real)
	private boolean contractividad;
	
	private List<Pair<Double, Double>> puntuaciones;// 1º puntuacion y 2º puntuacion acumulada
	//Valores de elite
	private CompFitness compFit;//Comparador de cromosomas por fitness
	private int tamElite;
	private boolean flagElit;
	
	private Plot2DPanel graf;

	public AlgoritmoGenetico(List<Cromosoma> poblIni, int maxGen, double probCruce, double probMut,
			String modoSel, String modoCruce, String modoMut, boolean flagElit, double pElit,boolean contractividad) {
		this.poblacion = poblIni;
		this.maxGen = maxGen;
		this.probCruce = probCruce;
		this.probMut = probMut;
		this.modoSel = modoSel;
		this.modoCruce = modoCruce;
		this.modoMut = modoMut;
		this.contractividad = contractividad;
		this.mejorCromosoma = this.poblacion.get(0);
		this.mejorGlobal = 80000;

		this.puntuaciones = new ArrayList<Pair<Double, Double>>();
		this.compFit = new CompFitness();
		this.flagElit = flagElit;
		if(flagElit)
			this.tamElite = (int) (this.poblacion.size() * pElit);
		else
			this.tamElite = 0;
	}

	public void geneticoAl() {
		
		int generacion = 0;
		List<Integer> seleccionados;
		List<Cromosoma> elite = null;
		Random r = new Random();
		List<Cromosoma> aux = new ArrayList<Cromosoma>();
		this.evalua();
		double[] mejorFitness = new double[this.maxGen];
		double[] mejorGeneracion = new double[this.maxGen];
		double[] mediaGeneracion = new double[this.maxGen];
		while (generacion < this.maxGen && this.condicion()) {
			
			//ELITE
			if(flagElit)
				elite = this.separaMejores();
			
			// Seleccion
			seleccionados = FactoriaAlgSeleccion.getAlgSeleccion(this.modoSel, r.nextDouble(), this.compFit)
					.seleccion(this.poblacion, this.puntuaciones);
			//Generacion nueva se copia sobre la poblacion
			aux.clear();
			
			for (int i = 0; i < this.poblacion.size(); i++) {
				aux.add(this.poblacion.get(seleccionados.get(i)).clone());
			}
			this.poblacion = new ArrayList<>(aux);
			
			
			
			// CRUCE
			
			List<Integer> cruzar = this.reproducir();
			List<Cromosoma> aux1 = new ArrayList<Cromosoma>();
			for (int i = 0; i < cruzar.size() - 1; i = i + 2) {

				
				aux1 = FactoriaCruce.getCruce(this.modoCruce).cruce(this.poblacion.get(cruzar.get(i)), this.poblacion.get(cruzar.get(i+1)));
				this.poblacion.set(cruzar.get(i), aux1.get(0));
				this.poblacion.set(cruzar.get(i + 1), aux1.get(1));

			}
			// MUTACION
			
			Mutacion mut = FactoriaMutacion.getMutacion(this.modoMut);
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
			if(this.contractividad) {
				if(generacion >= 1) {
					if(mediaGeneracion[generacion-1] > mediaGeneracion[generacion])generacion++;
				}else {
					generacion++;
				}
			}else {
			  generacion++;
			}
		}
		//Dibujamos la grafica antes de salir
		this.pintaGrafica(mejorFitness, mejorGeneracion, mediaGeneracion);
	}

	// Calcula la suma del fitness, el mayor fitness y la posición del mejor
	// individuo en la poblacion actual
	private void evalua() {
	
		double fit, punt = 0, puntAcum, peor = this.poblacion.get(0)
				.getFitness(), sumaFitness = 0;
		double mejorGener = 0;
		this.mejorGener = this.poblacion.get(0).getFitness();
		// Buscamos el mayor fitness y acumulamos la suma
		for (int i = 0; i < this.poblacion.size(); i++) {
			fit = this.poblacion.get(i).fitness();
			sumaFitness += fit;
			mejorGener = Math.max(mejorGener, fit);
			peor = Math.min(peor, fit);
		}

		this.mediaGener = sumaFitness / this.poblacion.size();
		if (this.puntuaciones.size() > 0)
			this.puntuaciones.clear();
		puntAcum = 0;

		double sumaFitAdap = 0;
		double fitAdap = 0;

		
		for (int i = 0; i < this.poblacion.size(); i++) {
			fit = this.poblacion.get(i).fitness();
			fitAdap = mejorGener - fit;
					
			sumaFitAdap += fitAdap;

			if (fit < this.mejorGlobal) { 
				this.mejorGlobal = fit;
				this.mejorCromosoma = this.poblacion.get(i);
			}
								
			if (fit < this.mejorGener)
				this.mejorGener = fit;
		

		}
		//double a = (1.5*this.mediaGener)/(this.mediaGener);
	//	double b = (1 - a)*this.mediaGener;

		for (int i = 0; i < this.poblacion.size(); i++) {
			fit = this.poblacion.get(i).fitness();
			fitAdap = mejorGener - fit;
		//	punt = a*fit +b;
			//punt = a + b *punt;
			punt = (fitAdap / sumaFitAdap);
			puntAcum += punt;
			this.puntuaciones.add(new Pair<>(punt, puntAcum));
		}
	}

	//Devuelve solo los seleccionados??? Los no que no se cruzan deben pasar tal cual TODO
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

	//Devuelve una copia de los mejores individuos (elite)
	private List<Cromosoma> separaMejores() {
		//Ordeno la poblacion por fitness
		this.poblacion.sort(this.compFit);
		List<Cromosoma> elite = new ArrayList<>();
		//Llenamos el array elite con copias de los mejores
		for(int i = 0; i < this.tamElite; i++) {
			elite.add(this.poblacion.get(i).clone());
		}
		return elite;
	}

	//Ordena la poblacion por fitness, y sustituye a los peores por la elite
	private void insertaElite(List<Cromosoma> elite){
		this.poblacion.sort(this.compFit);
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

	public double getMejor(){
		return this.mejorGlobal;
	}
	
	public List<String> pintarCamino() {
		List<String> caminoFinal = new ArrayList<String>();
		String camino = "";
		String camino1 = "";
		String camino2 = "";
		String camino3 = "";
		String fitness = "Mejor Fitnes: " + this.mejorGlobal;
		
		
		for(int i = 0; i < this.mejorCromosoma.getSize();i++) {
			if(i < 8) camino += this.mejorCromosoma.getGen(i).getNombreCiudad() + "-->";
			else if(i >= 8 && i < 15)  camino1 += this.mejorCromosoma.getGen(i).getNombreCiudad() + "-->";
			else if(i >= 15 && i < 23) camino2 += this.mejorCromosoma.getGen(i).getNombreCiudad() + "-->";
			else {
				if(i == this.mejorCromosoma.getSize() - 1) camino3 += this.mejorCromosoma.getGen(i).getNombreCiudad();
				else camino3 += this.mejorCromosoma.getGen(i).getNombreCiudad() + "-->";
			}
		}
		caminoFinal.add(camino);
		caminoFinal.add(camino1);
		caminoFinal.add(camino2);
		caminoFinal.add(camino3);
		caminoFinal.add(fitness);
		return caminoFinal;
		
	}
	
	private boolean condicion() {
		if(this.contractividad) {
			boolean acabar = true;
			boolean converge = false;
			int j = 0;
			int contador = 0;
			int contadorAux = 0;
			for(int i = 0;i < this.poblacion.size();i++) {
				for(int w = i;w < this.poblacion.size();w++) {
				j=0;
				contadorAux = 0;
				if(converge) {
					contador+=2;
					 converge = false;
				}
				while(j < this.poblacion.get(i).getSize() && !converge) {
					if(this.poblacion.get(i).getGen(j).getNombreCiudad().equals(this.poblacion.get(w).getGen(j).getNombreCiudad())) contadorAux++;
					if(contadorAux >= 26.0) converge = true;
					j++;
				}
			}
			}
			if(contador >= 5000) {
				acabar = false;
				
			}
			return acabar;
		}else {
			return true;
		}
	}
}
