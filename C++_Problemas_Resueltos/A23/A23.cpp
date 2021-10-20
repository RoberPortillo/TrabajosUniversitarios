#include <iostream>
#include <fstream>
#include <string>

using namespace std;

typedef int tVector[100000];


void tratarSolucion(tVector solucion,int tam){

	for (int i = 0; i < tam; i++){
		switch (solucion[i]){
		case 0: cout << "rojo ";
			break;
		case 1:cout << "azul ";
			break;
		case 2: cout << "verde ";
			break;
		}
	}
	cout << endl;
}
bool ok(tVector solucion, int m){
int i = 0;
int r = 0, a = 0, v = 0;
while (i < m){
if (solucion[i] == 0) r++;
else if (solucion[i] == 1) a++;
else v++;
i++;
}
if (r>a + v) return true;
else return false;
}


bool esSolucion(tVector solucion, int posRellenas, int tamanioTorre){
	//el numero de posiciones rellenas es el tamaño de la torre
	if (posRellenas == (tamanioTorre-1) && ok(solucion,tamanioTorre)) return true;

	return false;
}

bool esValida(tVector solucion, int posAInsertar, int a, int r, int v) {
	//no puede haber mas verdes (2) que azules (1) 
	//la primera siempre tiene que ser roja(0)
	//azules no puede ser mayor 
	int verdes = 0, azules = 0, rojas = 0;
	if (posAInsertar == 0){
		if (solucion[0] != 0) return false; // si la primera pos no es roja no vale
	}
	else{
		//no puede haber verdes(2) 2 seguidas
		if (solucion[posAInsertar] == 2 && solucion[posAInsertar - 1] == 2) return false;

		for (int i = 0; i<= posAInsertar; i++){
			if (solucion[i] == 0){
				rojas++;
			}
			else if (solucion[i] == 1){
				azules++;
			}
			else {
				verdes++;
			}
		}
		//no puede haber mas verdes (2) que azules (1)
		if (verdes > azules) return false;

		// no puede haber mas fichas de un color que las que indica el usurio
		if (azules > a || verdes > v || rojas > r) return false;
	}
	return true;
}





void variaciones(tVector solucion, int k, int r, int a, int v,int tam,bool &imprimir){
	
	for (int repeticiones = 0; repeticiones <3; repeticiones++){
		solucion[k] = repeticiones;

		if (esValida(solucion, k, a, r, v)){
			if (esSolucion(solucion, k, tam)){
				tratarSolucion(solucion,tam);
				imprimir= true;

			}
			
			else
				variaciones(solucion, k + 1, r, a, v,tam,imprimir);
		}
	}
	
}

int main() {
	tVector solucion;
	int rojo, verde, azul;
	int tam;
	bool imprimir=false;
	cin >> tam;
	while (tam >= 2){
		cin >> azul;
		cin >> rojo;
		cin >> verde;
		variaciones(solucion, 0, rojo, azul, verde, tam,imprimir);
		if (!imprimir) cout << "SIN SOLUCION\n";
		imprimir = false;
		cin >> tam;
	}
	
	system("pause");
	return 0;
}