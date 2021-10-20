package genAlg.Mutacion;

import java.util.List;
import java.util.Random;

import funciones.Arbol;
import funciones.Individuo;

public class MutacionPorInicialización extends Mutacion{

	@Override
	public Individuo muta(Individuo ab, double probMut) {
		Random r = new Random();
		if(r.nextDouble() < probMut) {
			int prof = (int) (Math.random() * ab.getArbol().getProf()) + 1;
			Individuo mutado = new Individuo(prof,ab.getMetIni());
			return mutado;
		}
		return ab;
	}

}
