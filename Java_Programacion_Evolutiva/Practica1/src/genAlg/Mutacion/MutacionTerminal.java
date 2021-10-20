package genAlg.Mutacion;

import java.util.Random;

import funciones.Arbol;
import funciones.Individuo;
import funciones.Terminal;

public class MutacionTerminal extends Mutacion{

	@Override
	public Individuo muta(Individuo prog, double probMut) {
		Random r = new Random();
		Individuo desc = prog.clone();
		if(r.nextDouble() < probMut) {
			Arbol aux = desc.getArbol();
			while(!aux.esTerminal()) {
				if(aux.getNodo().equals("P3")) {
					double rand = r.nextDouble();
					if(rand > 0.33 && rand < 0.66)
						aux = aux.getHi();
					else if(rand < 0.66)
						aux = aux.getHc();
					else
						aux = aux.getHd();
				}
				else
					aux = (r.nextDouble() < 0.5) ? aux.getHi() : aux.getHd();
			}
			
			boolean cambio = false;
			for(Terminal t : Terminal.values()) {
				if(!cambio && !t.toString().equals(aux.getNodo())) {
					aux.setNodo(t.toString(), true);
					cambio = true;
				}
			}
			
			desc.setArbol(aux);
		}
		
		return desc;
	}

}
