//Marcos Matute
//Roberto Portillo


#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

/*
*
*
* I = { 0 <= i <= v.Length &&  max = (forall j :: 0 <= j < i :: V[j] <= V[i] :: v[i]) && suma = (# k :: 0 <= k < i :: v[k] < max)
*
* Función de cota = v.Length - i
*
*  COSTE ASINTÓTICO:
*
* Teniendo en cuenta que las asignaciones realizadas dentro del bucle son constantes, se deduce que realmente
* el tiempo que vaya a tardar en ejecutarse el código va a depender del tamaño del vector quedando
* una expresión como esta x*tam donde x representa el coste de realizar el bucle y tam el número de veces que se ejecuta.
* De aquí deducimos que el coste del algoritmo es lineal con respecto al tamaño del vector.
*
*/

int resuelveCaso(vector<int> v) {
	int i = 0;
	int max = v[0];
	int contMax = 0;
	int suma = 0;

	while (i<int(v.size())) {
		suma = suma + v[i];

		if (v[i]>max) {
			max = v[i];
			contMax = 1;
		}

		else if (v[i] == max) contMax++;
		i++;
	}

	return suma - (max*contMax);
}

int main() {
	// Para la entrada por fichero.
#ifndef DOMJUDGE
	//std::ifstream in("casos.txt");
	//auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif

	vector<int> v;
	int s;
	int tam;
	int numCasos;
	cin >> numCasos;
	// Resolvemos
	for (int i = 0; i < numCasos; ++i) {
		cin >> tam;
		v.resize(tam);
		for (int i = 0; i<int(v.size()); i++) {
			cin >> v[i];
		}
		s = resuelveCaso(v);
		cout << s << "\n";
	}


#ifndef DOMJUDGE // para dejar todo como estaba al principio
	//std::cin.rdbuf(cinbuf);
	//system("PAUSE");
#endif

	return 0;
}