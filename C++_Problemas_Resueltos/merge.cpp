#include <iostream>
#include <vector>

using namespace std;

void mezcla(vector<int> &v, int a, int m, int b) {

	int * u = new int[b - a + 1];
	int i, j, k;

	for (k = a; k <= b; k++)
		u[k - a] = v[k];
	i = 0;
	j = m - a + 1;
	k = a;
}

void mergesort(vector<int> &v, int a, int b) {

	int m;

	if (a < b) {

		m = (a + b) / 2;
		mergesort(v, a, m);
		mergesort(v, m + 1, b);
		mezcla(v, a, m, b);
	}
}

int main() {

	vector<int> v = vector<int>();

	for (int i = 10; i > 0; i--)
		v.push_back(i);

	mergesort(v, 0, 9);



	for (int i = 0; i < v.size(); i++)
		cout << v[i] << " ";
	cout << endl;

	system("pause");
	return 0;
}