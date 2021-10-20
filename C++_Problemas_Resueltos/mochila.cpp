//MUY APROXIMADO, HARÍAN FALTA CASOS DE PRUEBA PARA COMPROBAR LA VALIDED DEL ALGORITMO


#include <iostream>
#include <fstream>

using namespace std;


const int TAM = 100;

typedef struct{
	int peso;
	int valor;
}tElemento;

typedef tElemento tLista[TAM];

void llenarLista(tLista lista, int &n, int &m) {

	ifstream file;
	file.open("casos.txt");

	if (file.is_open()) {
		int i = 0, aux1 = 0, aux2;
		file >> n;
		file >> m;
		while (i < n && aux1 > 0) {

			file >> aux1;
			if (aux1 != -1) {

				file >> aux2;
				lista[i].peso = aux1;
				lista[i].valor = aux2;
			}
			i++;
		}
	}
}

void copiarSolucion(int solucion[], int solucionOptima[], int n) {

	for (int i = 0; i < n; i++)
		solucionOptima[i] = solucion[i];
}

void mochila_VA(tLista lista, int k, int solucion[], int solucionOptima[], int n, int m, int &valor, int &peso, int &valorMejor) {

	//Caso de cojo el objeto k.
	solucion[k] = 1;
	peso += lista[k].peso;
	valor += lista[k].valor;
	if (peso < m) {

		if (k == n - 1) {

			if (valorMejor < valor) {
				valorMejor = valor;
				copiarSolucion(solucion, solucionOptima, n);
			}
		}
		else
			mochila_VA(lista, k+1, solucion, solucionOptima, n, m, valor, peso, valorMejor);
	}

	//Caso no cojo el objeto k.
	solucion[k] = 0;
	peso -= lista[k].peso;
	valor -= lista[k].valor;
	if (k == n - 1) {

		if (valorMejor < valor) {

			valorMejor = valor;
			copiarSolucion(solucion, solucionOptima, n);
		}
		else
			mochila_VA(lista, k + 1, solucion, solucionOptima, n, m, valor, peso, valorMejor);
	}
}








int main() {

	int solucion[TAM], solucionOptima[TAM], n, m, valor = 0, peso = 0, valorMejor = 0;
	tLista lista;

	llenarLista(lista, n, m);

	//Llamamos con k = 0, tamanio = 0, valor = 0 y valorMejor = 0
	mochila_VA(lista, 0, solucion, solucionOptima, n, m, valor, peso, valorMejor);

	for (int i = 0; i < n; i++) {

		cout << solucionOptima[i] << "--";
	}

	cout << "\n";

	system("pause");
	return 0;
}