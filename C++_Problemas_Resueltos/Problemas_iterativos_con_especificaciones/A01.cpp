#include <iostream>
using namespace std;

const int MAX = 10000;
typedef int tArray[MAX];



/*
P={0 <= tam <= 10000}


Q={contador = (#i :: 1 <= i < tam-1 : v[i - 1] > v[i] : v[i] < v[i + 1]  }


I = {contador >= 0 & 0 < i < tam & v[i - 1] > v[i] : v[i] < v[i + 1]}

Función de cota: T= tam -1 -i


*/
int contValles(tArray v, int tam){
	int contador = 0;
	int i = 1;
	while ( i < tam - 1){
		if (v[i - 1] > v[i] && v[i] < v[i + 1]) contador++;
		i++;
	}
	return contador;
}

/*
P={0 <= tam <= 10000}


Q = {contador = (#i :: 1 <= i < tam-1 : v[i - 1] < v[i] : v[i] > v[i + 1]  }


I = {contador >= 0 & 0 < i < tam & v[i - 1] < v[i] : v[i] > v[i + 1]}

Función de cota: T= tam -1 -i


*/
int contPicos(tArray v, int tam){
	int contador = 0;
	int i = 1;
	while ( i < tam - 1){
		if (v[i - 1] < v[i] && v[i] > v[i + 1]) contador++;
		i++;
	}
	return contador;
}

int main(){
	int casos, tam, contV, contP;
	tArray vector;
	cin >> casos;
	for (int i = 0; i < casos; i++){
		cin >> tam;
		for (int i = 0; i < tam; i++){
			cin >> vector[i];
		}
		contP = contPicos(vector, tam);
		contV = contValles(vector, tam);
		cout << contP << " " << contV << "\n";
	}
	system("pause");
	return 0;
}