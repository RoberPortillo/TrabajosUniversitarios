#include "autoAdjTree.h"

#include <fstream>
#include <time.h>

using namespace std;


int main() {

	ofstream timeFile;
	ifstream numbersFile;
	clock_t ini, fin;
	autoAdjTree<int> arbol;
	int num, veces, times[3];
	timeFile.open("tiempos.txt");
	//El bucle se ejecuta desde tamaño 1000 hasta 10000
	int tam = 1000;
	while (tam <= 10000) {
		veces = 0;
		//Ejecuta cada caso 3 veces para luego hacer una media y asi reducir las fluctuaciones
		while (veces < 3) {
			arbol = autoAdjTree<int>();
			numbersFile.open("numeros.txt");
			ini = clock();
			//Reliza tam inserciones de números escritos en un fichero previo
			for (int i = 0; i < tam; i++) {
				numbersFile >> num;
				arbol.insert(num);
			}
			//Realiza tam busquedas de números aleatorios
			for (int i = 0; i < tam; i++) {
				num = rand();
				arbol.buscar(num);
			}
			//Realiza tam borrados de números aleatorios
			for (int i = 0; i < tam; i++) {
				num = rand();
				arbol.borrar(num);
			}
			//Alamacena la medida en el array times del que luego se obtendrá una media
			fin = clock();
			times[veces] = (fin - ini);
			arbol.libera();
			numbersFile.close();
			veces++;
		}
		//Hace la media de las tres medidas
		int media = 0;
		for (int i = 0; i < 3; i++)
			media += times[i];
		media /= 3;
		//Escribe la medida en el fichero
		timeFile << tam << ' ' << media << '\n';
		tam += 1000;
	}
	timeFile.close();

	return 0;
}