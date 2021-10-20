package genAlg.Mutacion;

import java.util.List;
import java.util.Random;

import funciones.Arbol;
import funciones.Individuo;

public class MutacionPorPermutacion extends Mutacion{

	@Override
	public Individuo muta(Individuo ab, double probMut) {
		Random r = new Random();
		if(r.nextDouble() < probMut) {
			
			List<Arbol> funciones = ab.getArbol().getFunciones();
			if(!funciones.isEmpty()) {
				Arbol mut = funciones.get(r.nextInt(funciones.size()));
				
				if(mut.getNodo().equals("P3")) {
					Arbol aux = mut.getHi().clone();
					mut.setHi(mut.getHc().clone());
					mut.setHc(mut.getHd().clone());
					mut.setHd(aux);
				}else {
					Arbol aux = mut.getHi().clone();
					mut.setHi(mut.getHd().clone());
					mut.setHd(aux);
				}
				ab.setArbol(mut);
			}
	
		}
		return ab;
	}

}
