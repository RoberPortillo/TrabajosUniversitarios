#include <iostream>

using namespace std;

const int TAM = 8;

typedef int tTablero[TAM][TAM];

bool esValida(int k, tTablero tablero, int col) {

	bool valida = true;
	int h, i = 0;

	//Vertical
	while (valida && i < TAM)
		valida = (tablero[i++][col] == 0);

	//Horizontal
	i = 0;
	while (valida && i < TAM)
		valida = (tablero[k][i++] == 0);

	//Diagonal izquierda
	h = k;
	i = col;
	while (valida && h >= 0 && i >= 0)
		valida = (tablero[h--][i--] == 0);

	h = k;
	i = col;
	while (valida && h < TAM && i < TAM)
		valida = (tablero[h++][i++] == 0);

	//Diagonal derecha
	h = k;
	i = col;
	while (valida && h < TAM && i >= 0)
		valida = (tablero[h++][i--] == 0);

	h = k;
	i = col;
	while (valida && h >= 0 && i < TAM)
		valida = (tablero[h--][i++] == 0);


	return valida;
}

void mostrarSol(tTablero tablero) {

	for (int i = 0; i < TAM; i++) {
		for (int j = 0; j < TAM; j++) {

			cout << tablero[i][j] << "  ";
		}
		
		cout << "\n";
	}
	cout << "\n\n";
}

void ochoReinas_VA(int k, tTablero tablero) {

	for (int i = 0; i < TAM; i++) {

		if (esValida(k, tablero, i)) {

			tablero[k][i] = 1;
			if (k == TAM - 1)
				mostrarSol(tablero);
			else
				ochoReinas_VA(k + 1, tablero);
		}
		tablero[k][i] = 0;
	}
}

int main() {

	tTablero tablero;

	for (int i = 0; i < TAM; i++)
		for (int j = 0; j < TAM; j++)
			tablero[i][j] = 0;

	ochoReinas_VA(0, tablero);

	system("pause");
	return 0;
}