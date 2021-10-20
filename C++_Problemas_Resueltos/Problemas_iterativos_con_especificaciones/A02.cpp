#include <iostream>
using namespace std;

const int MAX = 100000;
typedef int tArray[MAX];



/*
P={0 <= tam <= 10000}


Q={contador = (#i :: 1 <= i < tam-1 : v[i - 1] > v[i] : v[i] < v[i + 1]  }


*/
int contValles(const tArray v, const int tam) {
	int contador = 0;
	int j;
	int i = 0;
	while (i < tam - 1) {
		j = i + 1;
		while (v[i] < v[j] && j < tam) {
			j++;
		}
		if (j < tam) {
			contador = contador + (j - i);
			i = j;
		}
		else i++;
	}
	return contador;
}


int main() {
	int casos, tam, contV;
	tArray vector;
	cin >> casos;
	for (int i = 0; i < casos; i++) {
		cin >> tam;
		for (int i = 0; i < tam; i++) {
			cin >> vector[i];
		}

		contV = contValles(vector, tam);
		cout << contV << "\n";
	}
	system("pause");
	return 0;
}