package genAlg.Cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import funciones.Arbol;
import funciones.Individuo;

public class CrucePorIntercambio extends Cruce{

	@Override
	public List<Individuo> cruce(Individuo prog1, Individuo prog2) {
		
		//Buscamos el punto de cruce en cada arbol guardando en una pila a los
		//antecesores y calculamos la diferencia de tamaño entre los subarboles
		Random rnd = new Random();
		Individuo desc1 = prog1.clone(), desc2 = prog2.clone();
		Arbol it1 = desc1.getArbol();
		List<Arbol> nodos = listaNodos(it1);
		if(!nodos.isEmpty())
			it1 = nodos.get(rnd.nextInt(nodos.size()));
			
		Arbol it2 = desc2.getArbol();
		nodos = listaNodos(it2);
		if(!nodos.isEmpty())
			it2 = nodos.get(rnd.nextInt(nodos.size()));

			
		int difSize1 = it2.getNumNodos() - it1.getNumNodos();
		int difSize2 = it1.getNumNodos() - it2.getNumNodos();
		
		Arbol aux = it1;
		while(aux != null) {
			aux.setNumNodos(aux.getNumNodos() + difSize1);
			aux = aux.getPadre();
		}
		
		aux = it2;
		while(aux != null) {
			aux.setNumNodos(aux.getNumNodos() + difSize2);
			aux = aux.getPadre();
		}
		
		//Buscamos que hijo en concreto ha perdido el ultimo antecesore y lo sustituimos
		Arbol padre1;
		if(it1.getPadre() == null) {
			desc1.setArbol(it2);
		}
		else {
			padre1 = it1.getPadre();
			if(padre1.getHi().getNodo().equals(it1.getNodo()))
				padre1.setHi(it2);
			else if(padre1.getHd().getNodo().equals(it1.getNodo()))
				padre1.setHd(it2);
			else if(padre1.getNodo().equals("P3") && padre1.getHc().getNodo().equals(it1.getNodo()))
				padre1.setHc(it2);
			else
				System.out.println("Error en el cruce");
			
			desc1.setArbol(padre1);
		}
		
		Arbol padre2;
		if(it2.getPadre() == null)
			desc1.setArbol(it2);
		else {
			padre2 = it2.getPadre();
			if(padre2.getHi().getNodo().equals(it2.getNodo()))
				padre2.setHi(it1);
			else if(padre2.getHd().getNodo().equals(it2.getNodo()))
				padre2.setHd(it1);
			else if(padre2.getNodo().equals("P3") && padre2.getHc().getNodo().equals(it2.getNodo()))
				padre2.setHc(it1);
			else
				System.out.println("Error en el cruce");
			
			desc2.setArbol(padre2);
		}
		
		List<Individuo> desc = new ArrayList<>();
		desc.add(desc1);
		desc.add(desc2);
		return desc;
		
	}
	
	private List<Arbol> listaNodos(Arbol it) {
		List<Arbol> ret = null;
		if(it != null) {
			ret = new ArrayList<>();
			//Si es funcion la probabilidad es mas alta
			if(!it.esTerminal()) {
				ret.add(it);
				ret.addAll(listaNodos(it.getHi()));
				ret.addAll(listaNodos(it.getHd()));
				if(it.getNodo().equals("P3"))
					ret.addAll(listaNodos(it.getHc()));
			}
		}
		return ret;
	}
}
