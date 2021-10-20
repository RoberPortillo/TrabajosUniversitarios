package genAlg.util;

import java.util.Comparator;

import funciones.Cromosoma;



public class CompFitness implements Comparator<Cromosoma>{

	@Override
	public int compare(Cromosoma cr1, Cromosoma cr2) {
		double fit1, fit2;
		fit1 = cr1.fitness();
		fit2 = cr2.fitness();
		if(fit1 > fit2)
			return 1;
		else if (fit1 == fit2)
			return 0;
		else
			return -1;
	}

}
