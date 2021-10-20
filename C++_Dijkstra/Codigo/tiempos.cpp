//Roberto Portillo Torres
#include <fstream>
#include <chrono>
#include <iostream>
#include "montDijkstra.cpp"
#include "generadorGrafos.cpp"

using namespace std;

void tiempo(const int N, fstream & file){

    grafo G = grafoAleatorio(N, 0.03);
    double costeMin[N];
    int predecesor[N];
    chrono::system_clock::time_point inicio;
    chrono::system_clock::time_point fin;
    chrono::duration<float, milli> tiempo;

    // Tres mediciones para cada tamaño del grafo
    for(int i = 0; i < 3; i++){

        inicio = chrono::system_clock::now();
        montDijkstra(G, N, costeMin, predecesor);
        fin = chrono::system_clock::now();
        tiempo += fin - inicio;
    }
    // Obtiene la media de las tres mediciones
    tiempo /= 3;

    // Escribe en el fichero dos datos en cada file: tamaño y tiempo
    if(file.is_open()){
        file << N;
        file << ' ';
        file << tiempo.count();
        file << '\n';
    }
    else
        cout << "ERROR EN LA ESCRITURA DEL FICHERO\n";
}

int main(){

    fstream file;
    file.open("tiempo.txt", fstream::app);

    for(int i = 100; i < 15000; i += 500)
        tiempo(i, file);
    
    file.close();
    return 0;
}
