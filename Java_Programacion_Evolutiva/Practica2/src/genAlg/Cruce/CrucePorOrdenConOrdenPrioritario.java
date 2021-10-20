package genAlg.Cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import funciones.Cromosoma;


public class CrucePorOrdenConOrdenPrioritario extends Cruce{

	@Override
	public List<Cromosoma> cruce(Cromosoma crom1, Cromosoma crom2) {
		List<Cromosoma> cruce = new ArrayList<Cromosoma>();
		List<Integer> pos1 = new ArrayList<Integer>();
		List<Integer> pos2 = new ArrayList<Integer>();
		List<Integer> posfinales = new ArrayList<Integer>();
		int aux;
		//Posiciones para 1º hijo
		for(int i = 0; i < 5 ; i++) {
			aux = (int) (Math.random() * crom1.getSize() - 1);
			while(aux == 0 || this.estaRepetido(pos1, aux)) aux = (int) (Math.random() * crom1.getSize() - 1);
			pos1.add(aux);
		}
		
		//Posiciones para 2º hijo
		for(int i = 0; i < 5 ; i++) {
			aux = (int) (Math.random() * crom2.getSize() - 1);
			while(aux == 0 || this.estaRepetido(pos2, aux)) aux = (int) (Math.random() * crom2.getSize() - 1);
			pos2.add(aux);
		}
		Cromosoma crom1C = crom1.clone();
		Cromosoma crom2C = crom2.clone();
		
		//Rellenamos el primer hijo
		posfinales = this.estaEnSubcadena(pos1, crom1, crom2);
		for(int i = 0; i < pos1.size(); i++) {
			crom2C.setGen(posfinales.get(i), crom1.getGen(pos1.get(i)));
		}
		//Rellenamos el segundo hijo
		posfinales = this.estaEnSubcadena(pos2, crom2, crom1);
		for(int i = 0; i < pos2.size(); i++) {
			crom1C.setGen(posfinales.get(i), crom2.getGen(pos2.get(i)));
		}
		cruce.add(crom1C);
		cruce.add(crom2C);

		return cruce;
	}

	public List<Integer> estaEnSubcadena(List<Integer> pos, Cromosoma padre1, Cromosoma padre2) {

		List<Integer> posiciones = new ArrayList<Integer>();
		boolean encontrado = false;
		int j = 0;
		for(int i = 0; i < pos.size(); i++) {
			j = 0;
			encontrado = false;
			while(j < padre2.getSize() && !encontrado) {
				if(padre1.getGen(pos.get(i)).getNombreCiudad().equals(padre2.getGen(j).getNombreCiudad())) {
					encontrado = true;
					posiciones.add(j);
				}
				j++;
			}
		}
		Collections.sort(posiciones);
		return posiciones;
	}
	public boolean estaRepetido(List<Integer> pos,int posicion) {
		boolean repetido = false;
		int i = 0;
		while(i < pos.size() && !repetido) {
			if(pos.get(i) == posicion) repetido = true;
			i++;
		}
		return repetido;
	}
}
