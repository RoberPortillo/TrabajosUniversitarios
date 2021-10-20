package genAlg.Cruce;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import funciones.*;

public class CrucePorRecombinacionDeRutas extends Cruce{

	private String[][] inmediatas;
	private boolean encontradaSol;
	private Comparator<String> menosConectado;
	private List<String> sol;
	
	@Override
	public List<Cromosoma> cruce(Cromosoma crom1, Cromosoma crom2) {
		this.creaMatriz(crom1, crom2);
		this.menosConectado = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(menosConectada(o1, o2))
					return 1;
				else
					return -1;
			}
		};
		//Generamos el descendiente 1
		this.sol = new ArrayList<>();
		Cromosoma desc1 = crom1.clone();
		this.encontradaSol = false;
		creaDesc(desc1, crom1);
		
		if(encontradaSol)
			this.codificaDesc(desc1);
		else
			desc1 = crom1.clone();
		
		//Generamos el descendiente 2
		Cromosoma desc2 = crom2.clone();
		this.encontradaSol = false;
		this.sol.clear();
		creaDesc(desc2, crom2);
		
		if(this.encontradaSol)
			this.codificaDesc(desc2);
		else
			desc2 = crom2.clone();
		
		List<Cromosoma> desc = new ArrayList<>();
		desc.add(desc1);
		desc.add(desc2);

		return desc;
	}
	
	private void creaDesc(Cromosoma desc, Cromosoma prog) {
		
		sol.add(prog.getGen(1).getNombreCiudad());
		
		for(int i = 2; i < prog.getSize(); i++) {
			//Recopilamos todos los posibles destinos y los ordenamos de menos conectados a mas
			int fila = desc.getGen(i - 1).getNumCiudad(), j = 1;
			List<String> posibles = new ArrayList<>();
			while(j < 5 && this.inmediatas[fila][j] != null) {		
				posibles.add(this.inmediatas[fila][j]);
				j++;
			}
			posibles.sort(this.menosConectado);
			//Mientras no encontremos solucion recorremos todos los destinos posibles
			j = 0;
			boolean end = false;
			while(j < posibles.size() && !end) {
				if(!sol.contains(posibles.get(j))) {
					sol.add(posibles.get(j));
					sol.add(prog.getGen(i).getNombreCiudad());
					end = true;
				}
				j++;
			}
			this.encontradaSol = (this.encontradaSol && end);
		}
	}
	
	
	
	private boolean menosConectada(String c1, String c2) {
		//Localizamos la fila c1
		int filaC1 = -1, i = 0;
		while(filaC1 == -1 && i < 27) {
			if(this.inmediatas[i][0].equals(c1))
				filaC1 = i;
			i++;
		}
		//Contamos sus ciudades inmediatas a c1
		int contC1 = 0;
		i = 1;
		while(i < 5 && this.inmediatas[filaC1][i] != null) {
			contC1++;
			i++;
		}
		//Localizamos la fila de c2
		int filaC2 = 0;
		i = 0;
		while(filaC2 == -1 && i < 27) {
			if(this.inmediatas[i][0].equals(c2))
				filaC2 = i;
			i++;
		}
		//Contamos las ciudades inmediatas a c2
		int contC2 = 0;
		i = 1;
		while(i < 5 && this.inmediatas[filaC2][i] != null) {
			contC2++;
			i++;
		}
		
		return contC1 < contC2;
	}
	
	private void creaMatriz(Cromosoma crom1, Cromosoma crom2) {
		
		this.inmediatas = new String[27][5];
		List<String> ciudades = Gen.getListaCiudades();
		boolean end, encontrada1, encontrada2;
		int j;
		//Para todas las ciudades
		for(int i = 0; i < ciudades.size(); i++) {
			end = false;
			encontrada1 = false;
			encontrada2 = false;
			j = 1;
			//Buscamos en los dos cromosomas
			while(!end && j < 28) {
				//Hemos encontrado la ciudad en el cromosoma 1
				if(ciudades.get(i).equals(crom1.getGen(j).getNombreCiudad())) {
					incluyeCiudad(i, j, crom1);
					encontrada1 = true;
				}
				//Hemos encontrado la ciudad en el cromosoma 2
				if(ciudades.get(i).equals(crom2.getGen(j).getNombreCiudad())) {
					incluyeCiudad(i, j, crom2);
					encontrada2 = true;
				}
				//end = true si hemos encontrado la ciudad en ambos cromosomas
				end = encontrada1 && encontrada2;
				j++;
			}
		}
		
	}
	
	//Incluye la ciudad del gen j del cromosoma en la posicion i de la matriz
	private void incluyeCiudad(int i, int j, Cromosoma crom) {
		
		//Añadimos la ciudad
		this.inmediatas[i][0] = crom.getGen(j).getNombreCiudad();
		//Comprobamos que las ciudades inmediatas no existan en la fila, w se queda en la primera posicion libre de la columna
		boolean existeAnt = false, existePost = false;
		int w = 1;
		//Si es la primera ciudad del camino diremos que la anterior existe para que no se tenga en cuenta
		if(j == 1)
			existeAnt = true;
		//Si es la ultima ciudad del camino diremos que la posterior no existe para que no se tenga en cuenta
		if(j == 27)
			existePost = true;
		while(this.inmediatas[i][w] != null) {
			if(j > 1 && !existeAnt)
				existeAnt = this.inmediatas[i][w].equals(crom.getGen(j - 1).getNombreCiudad());
			if(j < 27 && !existePost)
				existePost = this.inmediatas[i][w].equals(crom.getGen(j + 1).getNombreCiudad());
			w++;
		}
		//Si no existe la anterior la añadimos e incrementamos w
		if(!existeAnt) {
			this.inmediatas[i][w] = crom.getGen(j - 1).getNombreCiudad();
			w++;
		}
		//Si no existe la posterior la añadimos
		if(!existePost)
			this.inmediatas[i][w] = crom.getGen(j + 1).getNombreCiudad();
	}
	
	private void codificaDesc(Cromosoma desc) {
		for(int i = 0; i < this.sol.size(); i++) {
			desc.setGen(i + 1, new Gen(this.sol.get(i)));
		}
	}

}