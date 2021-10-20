//Roberto Portillo Torres
//Marcos Matute Fern�ndez


#include <iostream>
#include <fstream>
using namespace std;

const int MAX = 10000;
typedef long long tArray[MAX];

/*
* P=(tam >= 0 & fMayor = 0 & fMenor = 0)
*
* Q = ((forall i : 0 <= i < tam : menor <= v[i] <= mayor) & (fMayor=(#i : 0 <= i < tam : v[i] = mayor) 
* & (fMenor=(#i : 0 <= i < tam : v[i] = menor))
* 
* I = ((0 <= i <= tam) & (mayor >= v[i]) & (menor <= v[i]))
*
* Funci�n de cota: T = tam - i
*
*  COSTE ASINT�TICO:
* Contando tiempos de asignaci�n, comparaci�n y acceso a vector como una unidad obtenemos el siguiente calculo
* para el caso peor:
* Coste 5 antes de entrar en el bucle.
* Coste 11 en el cuerpo del bucle y coste 1 por cada comparaci�n.
* Coste 1 en la comparaci�n de salida del bucle.
* Sabiendo que el bucle se ejecuta "tam" veces donde "tam" es el tama�o del vector obtenemos la siguiente formula:
*
* Coste total = 5 + (11 + 1)*tam + 1 = 6 + 12*tam
*
* De aqu� deducimos que el coste del algoritmo es lineal con respecto al tama�o del vector.
*/

void criogenizacion(const tArray v, const int tam, long long &mayor, int &fMayor, long long &menor, int &fMenor){
	
	mayor = v[0];
	menor = v[0];
	int i = 0;
	while (i < tam){

		if (v[i] > mayor){
			mayor = v[i];
			fMayor = 1;
		}
		else if (v[i] == mayor){
			fMayor++;
		}
		if (v[i] < menor){
			menor = v[i];
			fMenor = 1;
		}
		else if (v[i] == menor){
			fMenor++;
		}
		i++;
	}
}


void resuelveCaso() {
	tArray v;
	long long aux;
	int tam = 0;
	long long mayor;
	int fMayor = 0;
	long long menor;
	int fMenor = 0;

	cin >> aux;
	while (aux != 0){
		v[tam] = aux;
		tam++;
		cin >> aux;;
	}

	criogenizacion(v, tam, mayor, fMayor, menor, fMenor);

	cout << menor << " " << fMenor << " " << mayor << " " << fMayor << "\n";

}

int main() {
	// Para la entrada por fichero.
#ifndef DOMJUDGE
	//std::ifstream in("casos.txt");
	//auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif

	int numCasos;
	cin >> numCasos;
	// Resolvemos
	for (int i = 0; i < numCasos; i++) {
		resuelveCaso();
	}


#ifndef DOMJUDGE // para dejar todo como estaba al principio
	//	std::cin.rdbuf(cinbuf);

#endif

	return 0;
}