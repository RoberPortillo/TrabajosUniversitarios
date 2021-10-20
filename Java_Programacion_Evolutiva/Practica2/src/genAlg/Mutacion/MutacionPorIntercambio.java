package genAlg.Mutacion;

import java.util.Random;

import funciones.Cromosoma;
import funciones.Gen;

public class MutacionPorIntercambio extends Mutacion{

	@Override
	public Cromosoma muta(Cromosoma crom, double probMut) {
		Random r = new Random();
		if(r.nextDouble() > probMut) {
			return crom;
		}else {
			Cromosoma mut = new Cromosoma(crom);
			int pos1 = (int) (Math.random() * crom.getSize() - 2) + 1;
			int pos2 = (int) (Math.random() * crom.getSize() - 2) + 1;
			while(pos1 == pos2 || pos1 == 0 || pos2 == 0) {
				pos1 = (int) (Math.random() * crom.getSize() - 2) + 1;
				pos2 = (int) (Math.random() * crom.getSize() - 2) + 1;
			}
			
			Gen gen1 = new Gen(mut.getGen(pos1));
			Gen gen2 = new Gen(mut.getGen(pos2));
			mut.setGen(pos2, gen1);
			mut.setGen(pos1, gen2);
			return mut;
		}
			
	}

}
