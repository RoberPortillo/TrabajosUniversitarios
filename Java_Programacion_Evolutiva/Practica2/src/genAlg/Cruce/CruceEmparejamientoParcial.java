package genAlg.Cruce;

import java.util.ArrayList;
import java.util.List;

import funciones.Cromosoma;
import funciones.Gen;

public class CruceEmparejamientoParcial extends Cruce {

	@Override
	public List<Cromosoma> cruce(Cromosoma crom1, Cromosoma crom2) {
		List<Cromosoma> cruce = new ArrayList<Cromosoma>();
		int corte1 = (int) (Math.random() * crom1.getSize() - 2) + 1;
		int corte2 = (int) (Math.random() * crom1.getSize() - 2) + 1;
		while (corte1 >= corte2 || corte1 == 0 || corte2 == 0) {
			corte1 = (int) (Math.random() * crom1.getSize() - 2) + 1;
			corte2 = (int) (Math.random() * crom1.getSize() - 2) + 1;
		}
		Cromosoma crom1C = crom1.clone();
		Cromosoma crom2C = crom2.clone();

		for (int i = corte1; i <= corte2; i++) {
			crom1C.setGen(i, crom2.getGen(i));
			crom2C.setGen(i, crom1.getGen(i));
		}

		for (int i = 1; i < crom1.getSize() - 1; i++) {
			if (i < corte1 || i > corte2) {

				if (this.estaEnSubcadena(corte1, corte2, crom1C, crom1C.getGen(i))) {
					crom1C.setGen(i,
							this.intercambio(crom1, crom2, crom1C.getGen(i), true, corte1, corte2, crom1C, crom2C));

				}
				if (this.estaEnSubcadena(corte1, corte2, crom2C, crom2C.getGen(i))) {
					crom2C.setGen(i,
							this.intercambio(crom1, crom2, crom2C.getGen(i), false, corte1, corte2, crom1C, crom2C));

				}

			}
		}

		cruce.add(crom1C);
		cruce.add(crom2C);

		return cruce;
	}

	public Gen intercambio(Cromosoma padre1, Cromosoma padre2, Gen valor, boolean caso, int corte1, int corte2,
			Cromosoma hijo1, Cromosoma hijo2) {
		int i = corte1;
		boolean encontrado = false;
		if (caso) {
			while (!encontrado) {
				if (padre2.getGen(i).getNombreCiudad().equals(valor.getNombreCiudad())) {
					encontrado = true;
				}

				if (encontrado && estaEnSubcadena(corte1, corte2, hijo1, padre1.getGen(i))) {
					valor = new Gen(padre1.getGen(i));
					i = corte1;
					encontrado = false;

				} else
					i++;

			}
			return padre1.getGen(i - 1);
		} else {

			while (!encontrado) {
				if (padre1.getGen(i).getNombreCiudad().equals(valor.getNombreCiudad())) {
					encontrado = true;
				}
				if (encontrado && estaEnSubcadena(corte1, corte2, hijo2, padre2.getGen(i))) {
					valor = new Gen(padre2.getGen(i));
					i = corte1;
					encontrado = false;

				} else
					i++;

			}
			return padre2.getGen(i - 1);
		}
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
