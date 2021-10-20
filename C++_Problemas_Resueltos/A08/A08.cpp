//Marcos Matute
//Roberto Portillo


#include <iostream>
#include <fstream>
using namespace std;

typedef int tVector[90000];

int resuelveCaso(tVector v, int m,int tam) {
	int min;
	if (m == 0 )min = m;
	else if (v[m - 1] < v[m]) min = resuelveCaso(v, m - 1,tam);
	else if (m == tam - 1) min = m;
	else if (v[m] > v[m + 1]) min = resuelveCaso(v, m + 1,tam);
	else min = m;
	return min;
}

int main() {
	// Para la entrada por fichero.
#ifndef DOMJUDGE
	//std::ifstream in("casos.txt");
	//auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif

	tVector v;
	int tam;
	int min;
	int m;
	int numCasos;
	cin >> numCasos;
	// Resolvemos
	for (int i = 0; i < numCasos; ++i) {
		cin >> tam;
		for (int j = 0; j < tam; j++) {
			cin >> v[j];
		}
		m = tam / 2;
		min = resuelveCaso(v, m,tam);
		cout << v[min] << "\n";
	}


#ifndef DOMJUDGE // para dejar todo como estaba al principio
	//std::cin.rdbuf(cinbuf);
	system("pause");
#endif

	return 0;
}