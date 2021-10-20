package funciones;

import java.util.ArrayList;
import java.util.List;

import funciones.Inicializacion.FactoriaInicializacion;

public class Arbol {

	String dato; // operando u operador
	Arbol Hi; // hijo izquierdo
	Arbol Hc; // hijo central
	Arbol Hd; // hijo derecho
	Arbol padre;
	int num_nodos; // número de nodos.
	int profundidad; // profundidad máxima del árbol 
	boolean tipo; //Funcion(0) o terminal(1)
	
	//La profundidad pasada por parametro para crearlo a de ser 0	
	public Arbol(int profund) {
		this.num_nodos = 0;
		this.profundidad = profund;
	}
	
	//Constructor por copia
	private Arbol(Arbol src) {
		
		if(src != null) {
			this.dato = src.dato;
			this.num_nodos = src.num_nodos;
			this.profundidad = src.profundidad;
			this.tipo = src.tipo;
			if(!this.tipo) {
				this.Hi = new Arbol(src.Hi);
				this.Hd = new Arbol(src.Hd);
				if(this.dato.equals("P3"))
					this.Hc = new Arbol(src.Hc);	
			}
		}
	}
	
	public Arbol clone () {
		return new Arbol(this);
	}
	
	public void creaArbol(String met) {
		FactoriaInicializacion.getMetodo(met).inicializa(this);
	}
	
	//Getters
	public List<Arbol> getFunciones(){
		List<Arbol> l = new ArrayList<>();
		if(!this.tipo) {
			l.add(this);
			l.addAll(this.getHi().getFunciones());
			l.addAll(this.getHd().getFunciones());
			if(this.dato.equals("P3"))
				l.addAll(this.getHc().getFunciones());
		}
		
		return l;
	}
	
	public boolean esTerminal() {
		return this.tipo;
	}
	
	public int getNumNodos() {
		return this.num_nodos;
	}
	
	public Arbol getHi() {
		return this.Hi;
	}
	
	public Arbol getHc() {
		return this.Hc;
	}
	
	public Arbol getHd() {
		return this.Hd;
	}
	
	public String getNodo() {
		return this.dato;
	}
	
	public int getProf() {
		return this.profundidad;
	}
	
	public Arbol getPadre() {
		return this.padre;
	}
	
	//Setters
	
	public void setPadre(Arbol a) {
		this.padre = a;
	}
	
	public void setHi(Arbol a) {
		this.Hi = a;
	}
	
	public void setHd(Arbol a) {
		this.Hd = a;
	}
	
	public void setHc(Arbol a) {
		this.Hc = a;
	}
	
	public void setNodo(String nodo, boolean tipo) {
		this.dato = nodo;
		this.tipo  = tipo;
	}
	
	public void setNumNodos(int num) {
		this.num_nodos = num;
	}
	
	//Basura por si sirve
	public boolean esTodoP3(List<Arbol> list) {
		int i = 0; 
		boolean ok = true;
		while(i < list.size() && ok) {
			if(list.get(i).getNodo().equals("P3")) ok = false;
			i++;
		}
		return ok;
	}
	
}
	