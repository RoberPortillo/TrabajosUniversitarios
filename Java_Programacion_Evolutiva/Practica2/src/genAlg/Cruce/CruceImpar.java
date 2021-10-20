package genAlg.Cruce;

import java.util.ArrayList;
import java.util.List;

import funciones.Cromosoma;
import funciones.Gen;

public class CruceImpar extends Cruce{

	@Override
	public List<Cromosoma> cruce(Cromosoma crom1, Cromosoma crom2) {
		
			List<Cromosoma> cruce = new ArrayList<>();
			Gen aux1, aux2;
			int pos1 = 1, pos2 = 1;
			for(int i = 1; i < crom1.getSize() - 1; i = i + 2) {
				
				aux1 = crom1.getGen(i);
				aux2 = crom2.getGen(i);
				
				for(int j = 1; j < crom1.getSize(); j++) {
					if(crom1.getGen(j).getNombreCiudad().equals(aux2.getNombreCiudad()))
						pos1 = j;
					if(crom2.getGen(j).getNombreCiudad().equals(aux1.getNombreCiudad()))
						pos2 = j;
				}
				
				crom1.setGen(pos1, crom1.getGen(i));
				crom1.setGen(i, aux2);
				
				crom2.setGen(pos2, crom2.getGen(i));
				crom2.setGen(i, aux1);
				
			}

			cruce.add(crom1);
			cruce.add(crom2);

			return cruce;
	}
}