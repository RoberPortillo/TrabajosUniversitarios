package genAlg.Cruce;

import java.util.ArrayList;
import java.util.List;

import funciones.Cromosoma;
import funciones.Gen;

public class CruceCiclos extends Cruce {

	@Override
	public List<Cromosoma> cruce(Cromosoma crom1, Cromosoma crom2) {
		List<Cromosoma> desc = new ArrayList<>();
		Cromosoma desc1, desc2;
		Gen aux;
		desc1 = crom1.clone();
		desc2 = crom2.clone();
		//Descendiente 1
		int i = 1, ini = crom2.getGen(i).getNumCiudad();
		while(i != 1) {
			aux = crom2.getGen(i);
			i = aux.getNumCiudad();
			if(i != 1)
				desc1.setGen(i, crom2.getGen(i));
		}
		//Descendiente 2
		i = 1; ini = crom1.getGen(i).getNumCiudad();
		while(i != 1) {
			aux = crom1.getGen(i);
			i = aux.getNumCiudad();
			if(i != 1)
				desc1.setGen(i, crom1.getGen(i));
		}
		desc.add(desc1);
		desc.add(desc2);
		
		return desc;
	}

}
