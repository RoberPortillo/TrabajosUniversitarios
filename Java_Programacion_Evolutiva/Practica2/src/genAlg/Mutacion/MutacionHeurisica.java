package genAlg.Mutacion;

import java.util.Random;

import funciones.Cromosoma;
import funciones.Gen;

public class MutacionHeurisica extends Mutacion{

	private Cromosoma desc;
	private Cromosoma mejorDesc;
	
	@Override
	public Cromosoma muta(Cromosoma crom, double probMut) {
		
		Random rnd = new Random();
		if(rnd.nextDouble() < probMut) {	
			//Generamos tres posiciones aleatorias del cromosoma, sin ser la primera ni la ultima (evitamos Madrid)
			int pos1, pos2, pos3;
			do {
				pos1 = rnd.nextInt(crom.getSize() - 1);
			}while(pos1 != 0);
			do {
				pos2 = rnd.nextInt(crom.getSize() - 1);
			}while (pos2 == pos1 && pos2 != 0);
			do {
				pos3 = rnd.nextInt(crom.getSize() - 1);
			}while((pos3 == pos2 || pos3 == pos1) && (pos2 != 0));
			
			this.mejorDesc = null;
			this.desc = crom.clone();
			this.evalua();
			
			for(int i = 0; i < 2; i++) {
				this.swapGen(pos1, pos2);
				this.evalua();
				
				this.swapGen(pos2, pos3);
				this.evalua();
			}
			
			this.swapGen(pos1, pos2);
			this.evalua();
			
			return this.mejorDesc;
		}
		
		return crom;
	}
	
	//Intercambia los genes
	private void swapGen(int pos1, int pos2) {
		Gen aux;
		aux = this.desc.getGen(pos1);
		this.desc.setGen(pos1, desc.getGen(pos2));
		this.desc.setGen(pos2, aux);
	}
	
	//Actualiza los mejores descendientes
	private void evalua() {
		if(this.mejorDesc == null || this.mejorDesc.getFitness() >= this.desc.getFitness())
			this.mejorDesc = this.desc;
	}

}
