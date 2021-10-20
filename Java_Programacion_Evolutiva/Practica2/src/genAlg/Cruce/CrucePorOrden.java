package genAlg.Cruce;

import java.util.ArrayList;
import java.util.List;

import funciones.Cromosoma;
import funciones.Gen;

public class CrucePorOrden extends Cruce {

	@Override
	public List<Cromosoma> cruce(Cromosoma crom1, Cromosoma crom2) {

		List<Cromosoma> cruce = new ArrayList<Cromosoma>();
		int corte1 = (int) (Math.random() * crom1.getSize() - 1);
		int corte2 = (int) (Math.random() * crom1.getSize() - 1);
		while (corte1 >= corte2 || corte1 == 0 || corte2 == 0) {
			corte1 = (int) (Math.random() * crom1.getSize() - 1);
			corte2 = (int) (Math.random() * crom1.getSize() - 1);
		}
		Cromosoma crom1C = crom1.clone();
		Cromosoma crom2C = crom2.clone();

		for (int i = corte1; i <= corte2; i++) {
			crom1C.setGen(i, crom2.getGen(i));
			crom2C.setGen(i, crom1.getGen(i));
		}


		int i = corte2 + 1;
		int cont = corte2 + 1;
		if(corte2 == 27) {
			i = 1;
			cont = 1;
		}
		while (i != corte1) {
			
				while (this.estaEnSubcadena(corte1, corte2, crom2, crom1.getGen(cont))) {
					cont = ((cont + 1) % 28) ;
					if(cont == 0) cont = 1;
					
				}
			
			
			crom1C.setGen(i, crom1.getGen(cont));
			cont = ((cont + 1) % 28);
			i = ((i + 1) % 28);
			if(i == 0) i = 1;
			if(cont == 0) cont = 1;

		}

		i = corte2 + 1;
		cont = corte2 + 1;
		if(corte2 == 27) {
			i = 1;
			cont = 1;
		}
		while (i != corte1) {
			
				while (this.estaEnSubcadena(corte1, corte2, crom1, crom2.getGen(cont))) {
					cont = ((cont + 1) % 28) ;
					if(cont == 0) cont = 1;
				
				}
	
			crom2C.setGen(i, crom2.getGen(cont));
			cont = ((cont + 1) % 28);
			i = ((i + 1) % 28);
			if(i == 0) i = 1;
			if(cont == 0) cont = 1;

		}

		cruce.add(crom1C);
		cruce.add(crom2C);

		return cruce;
	}

	public boolean estaEnSubcadena(int corte1, int corte2, Cromosoma subcadena, Gen gen) {
		boolean esta = false;
		int i = corte1;
		while (i <= corte2 && !esta) {
			if (subcadena.getGen(i).getNombreCiudad().equals(gen.getNombreCiudad())) {
				esta = true;
			}
			i++;
		}
		return esta;
	}

}
