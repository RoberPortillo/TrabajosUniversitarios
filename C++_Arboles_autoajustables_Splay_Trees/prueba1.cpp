#include "autoAdjTree.h"
#include <iostream>
using namespace std;


int main() {

	autoAdjTree<int> arbol = autoAdjTree<int>();

	int nums[] = { 5, 7, 9, 3, 2 };

	for (int i = 0; i < 5; i++) {
		arbol.insert(nums[i]);
		cout << "Insercion del numero: " << nums[i] << '\n';
		arbol.mostrar();
	}

	arbol.buscar(9);
	cout << "Tras buscar el numero 9:\n";
	arbol.mostrar();

	arbol.borrar(2);
	cout << "Tras borrar el numero 2:\n";
	arbol.mostrar();
	arbol.libera();
	system("pause");

	return 0;
}