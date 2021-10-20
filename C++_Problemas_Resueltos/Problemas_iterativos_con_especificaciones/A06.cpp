//Roberto Portillo
//Marcos Matute


#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

/*
* I = { 0 <= i <= v.length && p <= i && (contCeros + contUnos < v.length)}
*
* Función de cota = v.length - 1
*
* COSTE ASINTÓTICO
*
* Calculando el coste de las asignaciones, comparaciones y accesos a vector que se realizan de forma constante
* dentro del cuerpo del bucle y contando con que el bucle lleva a cabo tantas veces como tamaño tenga el vector
* obtenemos una fórmula del tipo:
*			coste = X*v.length
* 
* Donde X es el coste del cuerpo del bucle por lo tanto afirmamos que el algoritmo tiene coste lineal respecto
* al tamaño del vector.
*/





int resuelveCaso(vector<int> v) {
	int i = 0;
	int contUnos = 0;
	int contCeros = 0;
	bool equilibrio = true;
	int p;

	while (i < int(v.size())) {

		if (v[i] == 0) contCeros++;
		else if (v[i] == 1) contUnos++;

		if (contCeros != contUnos && equilibrio) {
			p = i;
		}

		equilibrio = (contCeros == contUnos);

		i++;
	}

	if (equilibrio) p = int(v.size()) - 1;
	else p--;

	return p;
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

	int p;
	int tam;
	vector <int> v;


	for (int i = 0; i < numCasos; ++i) {
		cin >> tam;
		v.resize(tam);
		for (int j = 0; j < int(v.size()); j++) {
			cin >> v[j];
		}
		p = resuelveCaso(v);
		cout << p << "\n";
	}

#ifndef DOMJUDGE // para dejar todo como estaba al principio
	//std::cin.rdbuf(cinbuf);
	//system("PAUSE");
#endif
	return 0;
}