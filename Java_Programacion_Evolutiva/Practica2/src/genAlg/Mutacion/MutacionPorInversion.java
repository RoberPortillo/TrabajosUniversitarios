package genAlg.Mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import funciones.Cromosoma;
import funciones.Gen;

public class MutacionPorInversion extends Mutacion{

	@Override
	public Cromosoma muta(Cromosoma crom, double probMut) {
		Random r = new Random();
		if(r.nextDouble() > probMut) {
			return crom;
		}else {
			Cromosoma mut = new Cromosoma(crom);
			List<Gen> aux = new ArrayList<Gen>();
			int pos1 = (int) (Math.random() * crom.getSize() - 2) + 1;
			int pos2 = (int) (Math.random() * crom.getSize() - 2) + 1;
			while(pos1 == pos2 || pos1 == 0 || pos2 == 0 || pos1 > pos2) {
				pos1 = (int) (Math.random() * crom.getSize() - 2) + 1;
				pos2 = (int) (Math.random() * crom.getSize() - 2) + 1;
			}
			
			for(int i = pos1; i <= pos2;i++ ) {
				aux.add(mut.getGen(i));
			}
			int cont = pos1;
			for(int j = aux.size() - 1; j >= 0; j-- ) {
				mut.setGen(cont, aux.get(j));
			}
			return mut;
		
		}
	}

}
