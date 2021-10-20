package genAlg.Mutacion;

import java.util.Random;

import funciones.Cromosoma;
import funciones.Gen;

public class MutacionPorInsercion extends Mutacion{

	@Override
	public Cromosoma muta(Cromosoma crom, double probMut) {
		Random r = new Random();
		if(r.nextDouble() > probMut) {
			return crom;
		}else {
			Cromosoma mut = new Cromosoma(crom);
			int elegida = (int) (Math.random() * crom.getSize() - 2) + 1;
			int insertar = (int) (Math.random() * crom.getSize() - 2) + 1;
			while(elegida == insertar || elegida == 0 || insertar == 0) {
				elegida = (int) (Math.random() * crom.getSize() - 2) + 1;
				insertar = (int) (Math.random() * crom.getSize() - 2) + 1;
			}
			
			Gen g = new Gen(mut.getGen(elegida));
			if(elegida < insertar) {
				for(int i = elegida;i < insertar;i++) {
					mut.setGen(i , mut.getGen(i + 1));
				}
				
			
			}else {
				
				for(int i = elegida; i > insertar;i--) {
					mut.setGen(i, mut.getGen(i - 1));
				}
			
		
			}
		
			mut.setGen(insertar, g);
			return mut;
		}
		
	}

}
