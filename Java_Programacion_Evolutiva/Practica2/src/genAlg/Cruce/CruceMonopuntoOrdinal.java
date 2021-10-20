package genAlg.Cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import funciones.Cromosoma;
import funciones.Gen;

public class CruceMonopuntoOrdinal extends Cruce {

	@Override
	public List<Cromosoma> cruce(Cromosoma crom1, Cromosoma crom2) {
		List<String> ciudades = Gen.getListaCiudades();
		
		int[] desc1Cod = new int[27];
		int[] desc2Cod = new int[27];
		
		//Codificamos el cromosoma 1
		for(int i = 0; i < 27; i++) {
			desc1Cod[i] = ciudades.indexOf(crom1.getGen(i + 1).getNombreCiudad());
			ciudades.remove(crom1.getGen(i + 1).getNombreCiudad());
		}
		//Reiniciamos la lista dinamica y codificamos el cromosoma 2
		ciudades = Gen.getListaCiudades();
		for(int i = 0; i < 27; i++) {
			desc2Cod[i] = ciudades.indexOf(crom2.getGen(i + 1).getNombreCiudad());
			ciudades.remove(crom2.getGen(i + 1).getNombreCiudad());
		}
		
		//Calculamos el punto de corte aleatorio e intercambiamos los valores de los descendientes hasta el punto
		int corte = (new Random()).nextInt(27), aux;
		for(int i = 0; i < corte; i++) {
			
			aux = desc1Cod[i];
			desc1Cod[i] = desc2Cod[i];
			desc2Cod[i] = aux;
		}
		//Reiniciamos la lista dinamica y decodificamos el descendiente 1
		Cromosoma desc1 = crom1.clone();
		ciudades = Gen.getListaCiudades();
		for(int i = 0; i < 27; i++) {
			desc1.setGen(i + 1, new Gen(ciudades.get(desc1Cod[i])));
			ciudades.remove(ciudades.get(desc1Cod[i]));
		}
		//Reiniciamos la lista dinamica y decodificamos el descendiente 2
		ciudades = Gen.getListaCiudades();
		Cromosoma desc2 = crom2.clone();
		for(int i = 0; i < 27; i++) {
			desc2.setGen(i + 1, new Gen(ciudades.get(desc2Cod[i])));
			ciudades.remove(ciudades.get(desc2Cod[i]));
		}
		
		List<Cromosoma> desc = new ArrayList<>();
		desc.add(desc1);
		desc.add(desc2);
		
		return desc;
	}

}
