package genAlg.Cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import funciones.Cromosoma;
import funciones.Gen;

public class CrucePorOrdenConPosicionesPrioritarias extends Cruce{

	@Override
	public List<Cromosoma> cruce(Cromosoma crom1, Cromosoma crom2) {
		List<Cromosoma> cruce = new ArrayList<Cromosoma>();
		List<Integer> pos = new ArrayList<Integer>();
		int aux;
		
		for(int i = 0; i < 5 ; i++) {
			aux = (int) (Math.random() * crom1.getSize() - 1);
			while(aux == 0 || this.estaRepetido(pos, aux)) aux = (int) (Math.random() * crom1.getSize() - 1);
			pos.add(aux);
		}
		Cromosoma crom1C = crom1.clone();
		Cromosoma crom2C = crom2.clone();
		Collections.sort(pos);
		
		for (int i = 0; i < 5; i++) {
			crom1C.setGen(pos.get(i),crom2.getGen(pos.get(i)));
			crom2C.setGen(pos.get(i), crom1.getGen(pos.get(i)));
		}

	
		int i = pos.get(0) + 1;
		int cont = pos.get(0) + 1;;
		
		while(this.avanzaI(i, pos)) {
			i = ((i + 1) % 28);
			
			if(i == 0) i = 1;
		
		}
		while (i != pos.get(0) ) {
		
			while(this.estaEnSubcadena(pos, crom2, crom1.getGen(cont))) {
				cont = ((cont + 1) % 28) ;
				if(cont == 0) cont = 1;
			}
			crom1C.setGen(i, crom1.getGen(cont));
			cont = ((cont + 1) % 28);
			i = ((i + 1) % 28);

			
			while(this.avanzaI(i, pos)){
				i = ((i + 1) % 28);			
				if(i== 0) i = 1;	
			}
			 if(i == 0) i = 1;
			if(cont == 0) cont = 1;

		}

		i = pos.get(0) + 1;
		cont = pos.get(0) + 1;
		
		while(this.avanzaI(i, pos)) i++;
		while (i != pos.get(0)) {
			
			while(this.estaEnSubcadena(pos, crom1, crom2.getGen(cont))) {
				cont = ((cont + 1) % 28) ;
				if(cont == 0) cont = 1;
			}
			crom2C.setGen(i, crom2.getGen(cont));
			cont = ((cont + 1) % 28);
			i = ((i + 1) % 28);

		
			while(this.avanzaI(i, pos)){
				i = ((i + 1) % 28);
			
				if(i== 0) i = 1;
			
			}
			if(i== 0) i = 1;
			if(cont == 0) cont = 1;

		
			
		}

		cruce.add(crom1C);
		cruce.add(crom2C);

		return cruce;
	}
	
	public boolean estaEnSubcadena(List<Integer> pos, Cromosoma hijo, Gen buscar) {

		//int posicion = -1;
		boolean encontrado = false;
		int i = 0;
		while( i < pos.size() && !encontrado) {
			if(buscar.getNombreCiudad().equals(hijo.getGen(pos.get(i)).getNombreCiudad())) {
				encontrado = true;
				//posicion = pos.get(i);
			}
			i++;
		}
	
		return encontrado;
	}

	public boolean avanzaI(int i,List<Integer> pos) {
		boolean avanza = false;
		int j = 1;
		while( j < 5 && !avanza) {
			if(i == pos.get(j)) {
				avanza = true;
			}
			j++;
		}
		return avanza;
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
	
	public boolean error(List<Integer> pos) {
		boolean avanza = false;
		int j = 0;
		while( j < 5 && !avanza) {
			if(0 == pos.get(j)) {
				avanza = true;
			}
			j++;
		}
		return avanza;
	}
}
