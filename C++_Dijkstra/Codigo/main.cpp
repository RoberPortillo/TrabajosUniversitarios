// ROBERTO PORTILLO TORRES

#include <iostream>
#include <string>
#include "montDijkstra.cpp"
#include "generadorGrafos.cpp"

using namespace std;

/*
* DESCRIPCION: Recorre el grafo mostrando por pantalla la lista de adyacencia
* COSTE: O(A + N)
*/
void mostrarGrafo(grafo const& G, int const N){

    cout << "\n- Lista de adyacencia del grafo generado:\n\n";
    for(int i = 0; i < N; i++){
        cout << "\tNodo " << i << "-> [";
        auto it = G[i].cbegin();
        while(it != G[i].cend()){
            cout << '(' << it->first << ',' << it->second << ")";
            it++;
            if(it != G[i].cend())
                cout << ", ";
        }
        cout << "]\n";
    }
}

int main(int argc, char *argv[]){

    grafo G;
    int N;
    char idGrafo = 'r';

    // En caso de introducir el nombre del fichero por parametros de entrada parte de esa informacion para generar el grafo
    if(argc == 2)
        idGrafo = argv[1][0];
    if(idGrafo == '1')
        generaGrafo(G, N, idGrafo);
    else if(idGrafo == '2')
        generaGrafo(G, N, idGrafo);

    // En caso contrario genera un grafo aleatorio con el numero de nodos establecido por el usuario
    else{
        cout << "\nIntroduce numero de nodos del grafo aleatorio a generar:\n";
        cin >> N;
        while(N <= 0){
            cout << "\nEl numero de nodos debe ser positivo:\n";
            cin >> N;
        }
        G = grafoAleatorio(N, 0.20);
    }

    // Inicializa los arrays pertinentes y llama al algoritmo dijkstra
    double costeMin[N];
    int predecesor[N];

    montDijkstra(G, N, costeMin, predecesor);

    // En caso de que el grafo sea aleatorio se da la opcion de mostrar la lista de adyacencia
    if(idGrafo == 'r'){

        cout << "¿Desea visualizar la lista de adyacencia del grafo generado automaticamente?\nS/N\n";
        cout << "Responder N le llevara directamente a la solucion de dijkstra\n";
        char resp;
        cin >> resp;
        resp = tolower(resp);
        if(resp == 's')
            mostrarGrafo(G, N);
    }

    // Imprime por pantalla el coste de los caminos desde la raiz hasta cada nodo y el predecesor de cada uno en el camino
    cout << "\n- Caminos minimos:\n\n";
    cout << "\tNodo" << "\t" << "CosteCamino" << "\t" << "Predecesor\n";
    for(int i = 0; i < N; i++){

        cout << "\t" << string(35, '-') << '\n';
        cout << " \t " << i << "\t     " << costeMin[i] << "\t\t    " << predecesor[i] << '\n';
    }
    cout << '\n';

    return 0;
}