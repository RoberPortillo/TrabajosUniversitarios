

//Marcos Matute y Roberto Portillo


#include <iostream>
#include <fstream>
#include <vector>
using namespace std;
/*
*
* P = {v.Length != 0 & 0 <= p < v.Length}
*
* Q = { b = (forall u, w :: 0 <= u <= p < w < v.Length ==> v[u] < v[w]}
*
* I = { 0 <= j <= v.Length & (forall u, w :: 0 <= u <= p < w < v.Length ==> v[u] < v[w]}
*
* T = v.Length - j + (if orden then 0 else 1)
*
* COSTE ASINTÓTICO:
* Contando tiempos de asignación, comparación y acceso a vector como una unidad obtenemos el siguiente calculo
* para el caso peor:
* Coste 4 antes de entrar en el bucle.
* Coste 6 en el cuerpo del bucle y coste 3 por cada comparación.
* Coste 2 en la comparación de salida del bucle.
* Sabiendo que el bucle se ejecuta "v.Length" veces donde "v.Length" es el tamaño del vector obtenemos la siguiente formula:
*
* Coste total = 4 + (6 + 3)*v.Length + 2 = 6 + 9*v.Lenght
*
* De aquí deducimos que el coste del algoritmo es lineal con respecto al tamaño del vector.
*
*/
bool problema3(vector<int> v, int p){
	int j = 0;
	int max = v[0];
	bool orden = true;

	while (j < int(v.size()) && orden){
		if (j <= p){
			if (v[j] > max) max = v[j];
		}
		else{
			orden = max < v[j];
		}
		j++;
	}
	return orden;
}

void resuelveCaso() {
	vector <int> v;
	int tam;
	int p;
	
	bool orden = false;
	cin >> tam;
	cin >> p;
	v.resize(tam);

	for (int i = 0; i < int(v.size()); i++){
		cin >> v[i];
	}
	if (tam != 0 && p < tam){
		orden = problema3(v, p);
	}
	if (!orden) cout << "NO\n";
	else cout << "SI\n";
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
	system("pause");
	return 0;
}