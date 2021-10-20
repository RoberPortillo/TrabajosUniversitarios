package funciones.Inicializacion;

import funciones.Arbol;
import funciones.Funcion;
import funciones.Terminal;

public class InicializacionCreciente extends Inicializacion {

	@Override
	public int inicializa(Arbol a) {
		Arbol aux;
		int nNodos = 0;
		if(a.getProf() > 0) {
			if(Math.random() > 0.5) {
				Funcion tip = Funcion.selecionaFuncion();
				a.setNodo(tip.toString(), false);
				nNodos++;
				if(a.getNodo().equals(Funcion.SIC.toString()) || a.getNodo().equals(Funcion.PROGN2.toString())) {		
					aux = new Arbol(a.getProf() - 1);
					aux.setPadre(a);
					nNodos += this.inicializa(aux);
					a.setHi(aux);
					aux = new Arbol(a.getProf() - 1);
					aux.setPadre(a);
					nNodos += this.inicializa(aux);
					a.setHd(aux);
				}else {
					aux = new Arbol(a.getProf() - 1);
					aux.setPadre(a);
					nNodos += this.inicializa(aux);
					a.setHi(aux);
					aux = new Arbol(a.getProf() - 1);
					aux.setPadre(a);
					nNodos += this.inicializa(aux);
					a.setHc(aux);
					aux = new Arbol(a.getProf() - 1);
					aux.setPadre(a);
					nNodos += this.inicializa(aux);
					a.setHd(aux);
				}
				
			}else {
				nNodos++;
				Terminal tip = Terminal.seleccionaTerminal();
				a.setNodo(tip.toString(), true);
			}		
			
		}else {
			//Las hojas estarán formadas por terminales
			nNodos++;
			Terminal tip = Terminal.seleccionaTerminal();
			a.setNodo(tip.toString(), true);
		}	
		
		a.setNumNodos(nNodos);
		
		return nNodos;
	}

	
}
