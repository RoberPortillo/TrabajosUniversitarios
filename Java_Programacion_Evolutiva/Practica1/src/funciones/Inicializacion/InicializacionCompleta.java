package funciones.Inicializacion;

import funciones.Arbol;
import funciones.Funcion;
import funciones.Terminal;

public class InicializacionCompleta extends Inicializacion {
	
	@Override
	public int inicializa(Arbol a) {
		
			Arbol aux;
			int nNodos = 0;
			if(a.getProf() > 1) {
				//Elije una funcion aleatoria puesto que no tiene profundidad 0
				Funcion tip = Funcion.selecionaFuncion();
				a.setNodo(tip.toString(), false);
				nNodos++;
				//Inicializa el hijo izquierdo y derecho con 1 menos de profundidad de forma recursiva
				aux = new Arbol(a.getProf() - 1);
				aux.setPadre(a);
				nNodos += this.inicializa(aux);
				a.setHi(aux);
				aux = new Arbol(a.getProf() - 1);
				aux.setPadre(a);
				nNodos += this.inicializa(aux);
				a.setHd(aux);
				//En caso de ser la funcion PROGN3 se inicializa tambien el hijo central
				if(a.getNodo().equals("P3")) {
					aux = new Arbol(a.getProf() - 1);
					aux.setPadre(a);
					nNodos += this.inicializa(aux);
					a.setHc(aux);
				}
					
			}else {
				//Las hojas (profundidad 0) estaran formadas por terminales
				nNodos++;
				Terminal tip = Terminal.seleccionaTerminal();
				a.setNodo(tip.toString(), true);
			}
			//Fija el numero de nodos
			a.setNumNodos(nNodos);
		
		//Devuelve el nº de nodos acumulados hasta este punto de la recursion 
		return nNodos;
	}

	
}
