//Marcos Matute
//Roberto Portillo


#include <iostream>
#include <fstream>
using namespace std;

typedef int tVector[10000];
/*
*
* P = { 1 <= tam < 10000 && exist k :: 0 <= k <tam :: altura < v[k] }
*
* fun resuelveCaso(tVector v, int tam, int altura, int &ini, int &fin) returns (ini int, fin int)
*
* Q= { exist ini, fin :: 0 <= ini <= fin < tam :: (forall u, w :: intervaloAlto(u, w) :: w - u <= fin - ini :: ini, fin)}
* intervaloAlto = {exist u, w :: 0 <= u <= w < tam :: (forall j :: u <= j <= w :: v[j] > altura))}
*
* I = { 0 <= i <=tam && -1 <= tamanio < tam && (exist ini, fin :: 0 <= ini <= fin <= i :: (forall u, w :: intervaloAlto(u, w) :: u - w <= fin - ini :: ini, fin))}
*
* Función de cota = tam - i
*
*  COSTE ASINTÓTICO:
*
* Teniendo en cuenta que las asignaciones realizadas dentro del bucle son constantes, se deduce que realmente
* el tiempo que vaya a tardar en ejecutarse el código va a depender del tamaño del vector quedando
* una expresión como esta x*tam donde x representa el coste de realizar el bucle y tam el número de veces que se ejecuta.
* De aquí deducimos que el coste del algoritmo es lineal con respecto al tamaño del vector.

*/
void resuelveCaso(tVector v, int tam, int altura, int &ini, int &fin) {
	int i = 0;
	ini = 0;
	fin = 0;
	int tamanio = -1;
	int tempI = 0;
	int tempF = 0;
	bool primer = false;
	while (i < tam) {
		if (v[i] > altura && !primer) {
			tempI = i;
			tempF = i;
			primer = true;
		}
		else if (v[i] > altura && primer) {
			tempF = i;
		}
		else if (v[i] <= altura && ((tempF - tempI) > (tamanio)) && primer) {
			ini = tempI;
			fin = tempF;
			tamanio = fin - ini;
			primer = false;
		}
		else primer = false;
		i++;
	}
	if (primer && ((tempF - tempI) > (tamanio))) {
		ini = tempI;
		fin = tempF;
	}

}

int main() {
	// Para la entrada por fichero.
#ifndef DOMJUDGE
	//std::ifstream in("casos.txt");
	//auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif

	tVector v;
	int tam = 0;
	int altura;
	int ini;
	int fin;
	int numCasos;
	std::cin >> numCasos;
	// Resolvemos
	for (int i = 0; i < numCasos; ++i) {
		cin >> tam;
		cin >> altura;
		for (int j = 0; j < tam; j++) {
			cin >> v[j];
		}
		resuelveCaso(v, tam, altura, ini, fin);
		cout << ini << " " << fin << "\n";
	}


#ifndef DOMJUDGE // para dejar todo como estaba al principio
	//std::cin.rdbuf(cinbuf);
	//system("pause");
#endif

	return 0;
}