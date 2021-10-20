// ROBERTO PORTILLO TORRES
//MARCOS MATUTE FERNANDEZ

#include "polinomio.h"

#include <string>
#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <stdexcept>
#include <math.h>

int resolucion( std::vector<polinomio>& polinomios, int valor,int cont){
	int solucion = 0;
	for (int i = 0; i < cont; i++){
		solucion = solucion + (polinomios[i].getCoeficiente()*(pow(valor,polinomios[i].getExponente())));
		
	}
	return solucion;
}


bool resuelveCaso() {
	std::vector<polinomio> polinomios;

	int coeficiente, exponente;
	int numCasos, num;
	int cont = 0;
	//Leer caso de prueba

	std::cin >> coeficiente;
	std::cin >> exponente;
	if (!std::cin)
		return false; 
	else{
		while (coeficiente != 0 || exponente != 0){
			polinomios.push_back(polinomio(coeficiente, exponente));
			cont++;
			std::cin >> coeficiente;
			std::cin >> exponente;

		}
		std::cin >> numCasos;
		for (int i = 0; i < numCasos; i++){
			std::cin >> num;
			int solucion = resolucion(polinomios, num, cont);
			std::cout << solucion << " ";
		}
		std::cout << "\n";







		//Resolver problema


		//Escribir resultado
		return true;
	}
}



int main() {

	// ajuste para que cin extraiga directamente de un fichero
#ifndef DOMJUDGE
	std::ifstream in("casos.txt");
	auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif

	while (resuelveCaso());

	// restablecimiento de cin
#ifndef DOMJUDGE
	std::cin.rdbuf(cinbuf);
	system("pause");
#endif
	return 0;
}