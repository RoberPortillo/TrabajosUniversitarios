#include <iostream>
#include <vector>

using namespace std;

void particion(vector<int> &v, int a, int b, int &p) {

	int i = a + 1, j = b, aux;
	
	while (i <= j) {

		if ((v[i] > v[a]) && (v[j] < v[a])) {
			aux = v[i];
			v[i] = v[j];
			v[j] = aux;
			i++;
			j--;
		}
		else {
			if (v[i] <= v[a]) i++;
			if (v[j] >= v[a]) j--;
		}
	}

	p = j;
	aux = v[a];
	v[a] = v[p];
	v[p] = aux;
}

void quicksort(vector<int> &v, int a, int b) {

	int p;

	if (a <= b) {
		particion(v, a, b, p);
		quicksort(v, a, p - 1);
		quicksort(v, p + 1, b);
	}
}



int main() {

	vector<int> v = vector<int>();
	for (int i = 10; i > 0; i--) {
		v.push_back(i);
	}

	quicksort(v, 0, 9);

	for (int i = 0; i < v.size(); i++) {
		cout << v[i] << " ";
	}

	cout << "\n";

	system("pause");
	return 0;
}