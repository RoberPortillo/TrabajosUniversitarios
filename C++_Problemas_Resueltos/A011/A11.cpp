//Marcos Matute Fernández
//Roberto Portillo Torres

#include <iostream>
#include <vector>

using namespace std;

/*
* P {v.size() >= 0 && v.size() == pot 2}
*
* Q {
*
* Ecuaciones:
*
* C0 si n = 0
*
* C1 + T(n/2) + T(n/2) si n > 0
*
*/

bool resuelveCaso(vector<int> v, int a, int b){

	bool ordenado;
	int m;
	if (a == b) ordenado = true;


	else{
		m = (a + b) / 2;


		ordenado = (v[a] <= v[m + 1] && v[b] >= v[m]);
		if (ordenado) ordenado = resuelveCaso(v, a, m);
		if (ordenado) ordenado = resuelveCaso(v, m + 1, b);
	}

	return ordenado;
}

int main(){

	bool salir = false;
	int num;
	vector <int> v;
	int a, b;
	v.resize(0);
	bool ordenado;
	while (!salir){
		cin >> num;
		while (num != 0){

			v.push_back(num);
			cin >> num;
		}
		salir = (v.size() == 0);
		if (!salir){
			a = 0;
			b = v.size() - 1;

			ordenado = resuelveCaso(v, a, b);

			if (ordenado) cout << "SI\n";
			else cout << "NO\n";

			v.resize(0);
		}

	}
	return 0;
}