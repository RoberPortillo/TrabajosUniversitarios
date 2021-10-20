
// NOMBRE Y APELLIDOS

#include <iostream>
#include <vector>
#include <fstream>
using namespace std;

int opera(int op1, int op2, int i) {

	int sol;

	switch (i) {
	case 0: 
		sol = op1 + op2;
		break;
	case 1:
		sol = op1 - op2;
		break;
	case 2:
		sol = op1 * op2;
		break;
	case 3:
		if (op2 > 0)
			sol = op1 / op2;
		else
			sol = op2;
		break;
	default:
		sol = op2;
		break;
	}

	return sol;
}



void cifrasYoperandosVA(vector<int> numeros, int sol[], int K, int cent,  bool& resuelto) {

	if (K < 5) {

		for (int i = 0; i < 4; i++) {
			if (!resuelto) {
				
				sol[K] = opera(numeros.at(K), sol[K-1], i);
				resuelto = (sol[K] == cent) && (K == 4);

				cifrasYoperandosVA(numeros, sol, K + 1, cent, resuelto);
		
			}
		}
	}
}


bool resuelveCaso() {

	//Leer caso de prueba
	vector<int> numeros;
	int sol[5];
	bool resuelto = false;
	int K = 1, cent, aux;

	cin >> cent;
	for (int i = 0; i < 5; i++) {

		cin >> aux;
		numeros.push_back(aux);
	}

	sol[0] = numeros.at(0);

	if (!cin)
		return false;

	//Resolver problema
	cifrasYoperandosVA(numeros, sol, K, cent, resuelto);
	//Escribir resultado
	if (resuelto)
		cout << "SI\n";
	else
		cout << "NO\n";

	return true;
}


int main() {

	// ajuste para que cin extraiga directamente de un fichero

	while (resuelveCaso());

	// restablecimiento de cin
	return 0;
}