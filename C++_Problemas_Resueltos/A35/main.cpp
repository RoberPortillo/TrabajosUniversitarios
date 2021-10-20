//ROBERTO PORTILLO TORRES
//MARCOS MATUTE FERNANDEZ

#include "complejo.h"

#include <iostream>
#include <fstream>

using namespace std;

void mandelbrot(complejo<float> const& comp, int & cont, complejo<float> & sucesion) {

	sucesion.operator*(sucesion);
	sucesion.operator+(comp);
	if (cont > 0 && sucesion.mod() <= 2)
		mandelbrot(comp, --cont, sucesion);
}

void resuelveCaso() {

	float a, b;
	int cont;
	std::cin >> a >> b >> cont;

	complejo<float> comp = complejo<float>(a, b);
	complejo<float> sucesion = complejo<float>();

	mandelbrot(comp, cont, sucesion);

	if (cont > 0 || (comp.mod() > 2))
		cout << "NO\n";
	else
		cout << "SI\n";
}

int main() {
	// Para la entrada por fichero.
#ifndef DOMJUDGE
	std::ifstream in("casos.txt");
	auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif


	int numCasos;
	std::cin >> numCasos;
	// Resolvemos
	for (int i = 0; i < numCasos; ++i) {
		resuelveCaso();
	}


#ifndef DOMJUDGE // para dejar todo como estaba al principio
	std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif

	return 0;
}