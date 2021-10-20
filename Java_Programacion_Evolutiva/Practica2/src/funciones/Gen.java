package funciones;


import java.util.ArrayList;
import java.util.List;

public class Gen {

	//Tenemos 27 ciudades si descontamos que empezamos en madrid y acabamos en madrid
	private final static String[] Ciudades = { "Alicante", "Almería", "Ávila", "Badajoz", "Barcelona", "Bilbao",
		"Burgos", "Cáceres", "Cádiz", "Castellón", "Ciudad Real", "Córdoba", "A coruña", "Cuenca", "Gerona",
		"Granada", "Guadalajara", "Huelva", "Huesca", "Jaén", "León", "Lérida", "Logroño", "Lugo", "Madrid",
		"Málaga", "Murcia","Albacete"};

	String nombreCiudad;
	int posFDistancia;
	int posCDistancia;

	
	//Murcia en la posicion de columna tendra el 27 y dara error de acceso, tener cuidado al calcular el fitness
	//Albacete es la -1 0
	//Se van eligiendo todas menos Madrid
	//Constructora que crea un gen aleatorio, teniendo en cuenta las ciudades ya seleccionadas para evitar repeticion
	public Gen(List<Boolean> ciudadesSeleccionadas) {
		int ciudad = (int) (Math.random() * 28);
		while(ciudad == 24 || !ciudadesSeleccionadas.get(ciudad)) {
			ciudad = (int) (Math.random() * 28);
		}
		if(ciudad == 27) {
			ciudadesSeleccionadas.set(ciudad, false);
			this.nombreCiudad = Ciudades[ciudad];
			this.posFDistancia = -1;
			this.posCDistancia = 0;
		}else {
			ciudadesSeleccionadas.set(ciudad, false);
			this.nombreCiudad = Ciudades[ciudad];
			this.posFDistancia = ciudad;
			this.posCDistancia = ciudad+1;
		}
				
	}
	
	//Constructora que crea un gen en base al indice de la ciudad en la lista
	public Gen(int indice) {
		this.nombreCiudad = Ciudades[indice];
		this.posFDistancia = indice;
		this.posCDistancia = indice + 1;
	}
	
	//Constructora que crea un gen en base a la ciudad de la lista
	public Gen(String ciudad) {
		this.nombreCiudad = ciudad;
		
		if(ciudad.equals("Albacete")) {
			this.posFDistancia = -1;
			this.posCDistancia = 0;
		}
		else {
			int idx = 0;
			boolean end = false;
			while(idx < 28 && !end) {
				end = Gen.Ciudades[idx].equals(ciudad);
				if(!end)
					idx++;
			}
			this.nombreCiudad = ciudad;
			this.posFDistancia = idx;
			this.posCDistancia = idx + 1;
		}
	}
	
	//Constructora por copia
	public Gen(Gen src) {
		this.nombreCiudad = src.nombreCiudad;
		this.posFDistancia = src.posFDistancia;
		this.posCDistancia = src.posCDistancia;
	}
	
	public String getNombreCiudad() {
		return this.nombreCiudad;
	}
	
	//Devuelve el numero de la ciudad en la lista, -1 si no la encuentra
	public int getNumCiudad() {
		int ret = -1, i = 0;
		while(ret == -1 && i < 28) {
			if(Gen.Ciudades[i].equals(this.nombreCiudad))
				ret = i;
			i++;
		}
		if(ret > 24)
			ret--;
		return ret;
	}
	
	//Devuelve una lista dinamica de todas las ciudades excepto Madrid
	public static List<String> getListaCiudades(){
		List<String> ciudades = new ArrayList<>();
		for(int i = 0; i < 28; i++) {
			if(i != 24)
				ciudades.add(Gen.Ciudades[i]);
		}
		
		return ciudades;
	}
	
	//Devuelve el indice de la lista para una ciudad dada
	public static int getIdx(String ciudad) {
		int ret = -1, i = 0;
		while(ret == -1 && i < 28) {
			if(Gen.Ciudades[i].equals(ciudad))
				ret = i;
			i++;
		}
		if(ret > 24)
			ret--;
		return ret;
	}
}
