#include "autoAdjTree.h"
#include <iostream>
using namespace std;

int main() {

	autoAdjTree<int> arbol = autoAdjTree<int>();
	int nums[] = { 5, 7, 9, 15, 20, 3, 2, 18, 12, 1 };

	for (int i = 0; i < 10; i++) {
		arbol.insert(nums[i]);
		cout << "Insercion del numero: " << nums[i] << '\n';
		arbol.mostrar();
	}

	arbol.buscar(9);
	cout << "Buqueda del 9:\n";
	arbol.mostrar();
	arbol.buscar(15);
	cout << "Busqueda del 15:\n";
	arbol.mostrar();

	arbol.borrar(7);
	cout << "Borrado del 7:\n";
	arbol.mostrar();
	arbol.borrar(12);
	cout << "Borrado del 12:\n";
	arbol.mostrar();

	arbol.libera();
	system("pause");
	return 0;
}