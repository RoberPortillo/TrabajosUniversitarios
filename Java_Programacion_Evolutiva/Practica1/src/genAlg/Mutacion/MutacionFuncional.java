package genAlg.Mutacion;

import java.util.List;
import java.util.Random;

import funciones.Arbol;
import funciones.Funcion;
import funciones.Individuo;

public class MutacionFuncional extends Mutacion{

	@Override
	public Individuo muta(Individuo ab, double probMut) {
		Random r = new Random();
		boolean end = false;
		if(r.nextDouble() < probMut) {
			mutaRecur(ab.getArbol(), r, end);
		}
		return ab;
	}
	
	private void mutaRecur(Arbol ab, Random r, boolean end) {
		
		//Caso base
		if(ab != null) {
			//Probabilidad de escoger este nodo
			if(r.nextDouble() < 0.2) {
				//Caso es SIC ponemos un P2
				if(ab.toString().equals("SIC")) {
					ab.setNodo("P2", false);
					end = true;
				}
				//Caso es un P2 ponemos un SIC
				else if(ab.toString().equals("P2")) {
					ab.setNodo("SIC", false);
					end = true;
				}
				//Si es un P3 seguimos con end en false
			}
			//Llamadas recursivas
			else{
				mutaRecur(ab.getHi(), r, end);
				mutaRecur(ab.getHd(), r, end);
				if(ab.getNodo().equals("P3"))
					mutaRecur(ab.getHc(), r, end);
			}
		}
	}

} 
