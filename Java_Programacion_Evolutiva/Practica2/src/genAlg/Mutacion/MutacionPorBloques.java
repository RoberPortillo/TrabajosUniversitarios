			package genAlg.Mutacion;


import java.util.Random;

import funciones.Cromosoma;
import funciones.Gen;

public class MutacionPorBloques extends Mutacion {

	@Override
	public Cromosoma muta(Cromosoma crom, double probMut) {
		
		Random rnd = new Random();
		if(rnd.nextDouble() < probMut) {
			//Entercambiamos dos bloques de genes de tamaño 3
			Cromosoma mut = new Cromosoma(crom);
			int pos1 = (int) (Math.random() * crom.getSize() - 2) + 1;
			int pos2 = (int) (Math.random() * crom.getSize() - 2) + 1;
			while(pos1 == pos2 || pos1 == 0 || pos2 == 0 || pos1 > pos2 - 4 || pos2 > crom.getSize() - 4) {
				pos1 = (int) (Math.random() * crom.getSize() - 2) + 1;
				pos2 = (int) (Math.random() * crom.getSize() - 2) + 1;
			}
			
			Gen aux;
			for(int i = 0; i < 3; i++) {
				
				aux = crom.getGen(pos1 + i);
				crom.setGen(pos1 + i, crom.getGen(pos2 + i));
				crom.setGen(pos2 + i, aux);
			}
		}
		
		return crom;
	}

}