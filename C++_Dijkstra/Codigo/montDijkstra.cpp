//ROBERTO PORTILLO TORRES

#ifndef MONTDISJKTRA_CPP
#define MONTDISJKTRA_CPP

#include <vector>
#include "monticuloSesgado.h"

using namespace std;

// Tipo utilizado para definir una arista <destino, valor>
typedef pair<int,double> par;

/*
* Tipo utiliziado para definir un grafo dirigido valorado, lista de listas de 
* par <int, double> (apoyandose en la clase predefinida vector)
*/
typedef vector<vector<par>> grafo;

/*
* Algoritmo de Dijkstra con monticulo para obtener los caminos mas cortos desde un nodo origen
* hasta los demas en un grafo valorado.
*
* PARAMETROS ENTRADA: 
*  G (lista de listas de pares(entero, real): grafo representado como una lista de adyacencia
*  N (entero): numero de nodos del grafo
*
* PARAMETROS SALIDA:
*  costeMin (lista de reales): coste del camino para llegar desde el origen hasta el nodo i-esimo
*  predecesor (lista enteros): nodo previo al i-esimo en el camino mas corto desde el origen
*
* COSTE:
*  O((A+N)*log N)
*/
void montDijkstra(const grafo &G, const int N, double costeMin[], int predecesor[]){

    monticuloSesgado M = monticuloSesgado();
    par minimo;
    vector<par> l;
    costeMin[0] = 0;
    predecesor[0] = 0;

    //Inicializa los N costes al máximo valor y los predecesores al nodo origen
    for(int i = 1; i < N; i++){

        costeMin[i] = __DBL_MAX__;
        predecesor[i] = 0;
        M.insertar(par(i, __DBL_MAX__));
    }

    //Actualiza el coste de los adyacentes al primer vertice
    auto it = G[0].cbegin();
    int dest;
    while(it != G[0].cend()){
        dest = it->first;
        costeMin[dest] = it->second;
        M.decrecerClave(dest, costeMin[dest]);
        it++;
    }

    int elegido;
    double coste;
    int nextToMin;

    for(int i = 0; i < N-2; i++){

        // Elige el nodo más cercano a la raiz (que no haya sido escogido antes)
        minimo = M.minimo();
        M.eliminarMinimo();
        elegido = minimo.first;

        // Recorre todos los nodos adyacentes al nodo elegido
        it = G[elegido].cbegin();
        while(it != G[elegido].cend()){

            nextToMin = it->first;
            coste = costeMin[elegido] + it->second;
            // Si el coste hasta los nodos pasando por el elegido es menor del que tienen asignado se actualiza su coste y su predecesor.
            if(coste < costeMin[nextToMin]){

                costeMin[nextToMin] = coste;
                predecesor[nextToMin] = elegido;
                M.decrecerClave(nextToMin, coste);
            }
            it++;
        }
    }
    //Libera la memoria dinamica del monticulo
    M.libera();
}
#endif