// ROBERTO PORTILLO TORRES

#ifndef GENERADORGRAFOS_CPP
#define GENERADORGRAFOS_CPP
#include <fstream>
#include <utility>
using namespace std;

/*
* DESCRIPCION:
*   Genera un grafo dirigido aleatorio en formato de lista de adyacencia acorde a los parametros.
*
* PARAMETROS DE ENTRADA:
*   N (entero) = numero de nodos del grafo
*   probA (real 0-1) = Probabilidad de establecer una arista entre dos nodos
*
* RETORNO:
*   G = El grafo generado
*
* COSTE:
*   O(N^2)
*/
grafo grafoAleatorio(int N, double probA){

    grafo G = grafo();
    G.resize(N);
    double valor, rndA;

    //Recorremos todas las combinaciones de nodos para comprobar la probabilidad de conexion
    for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
            
            rndA = (double) rand()/RAND_MAX;
            //Si el aleatorio generado es menor que A genera arista
            if(rndA < probA){

                //El valor asignado a la arista esta acotado entre 0 y 20
                valor = (rand() % 30) + 5;
                G[i].push_back(par(j, valor));
            }
        }
    }

    return G;
}

/*
* DESCRIPCION:
*   Genera un grafo en base al archivo recibido por parametro
* 
* PARAMETROS ENTRADA:
*   mode: numero del nombre del archivo que contiene la informacion del grafo
*
*   Tipo del fichero:
*       - ubicación: mismo directorio en el que esten los ficheros de codigo fuente
*       - nombre: grafo[mode].txt
*       - primera linea entero indicando el numero de nodos
*       - resto de lineas de tres numeros indicando cada arista ===> [nodo origen] [nodo destino] [valor]
*
*
* PARAMETROS SALIDA:
*   G: grafo dirigido y valorado generado en formato de lista de adyacencia
*   N(entero): Numero de nodos del grafo
*/
void generaGrafo(grafo &G, int &N, char mode){

    fstream file;
    string fName;
    int src, dest;
    double val;
    //Genera el nombre del archivo
    fName = "grafo";
    fName += mode;
    fName += ".txt";
    file.open(fName);

    //Muestra error en caso de no poder abrir el archivo
    if(!file.is_open())
        cout << "ERROR: No se encontro el archivo grafo" << mode << ".txt";
    else{
  
        G = grafo();
        file >> N;
        G.resize(N);
        while(file.is_open()){

            file >> src;
            file >> dest;
            file >> val;

            if(!file.eof())
                G[src].push_back(par(dest, val));
            else
                file.close();
        }
    }
}
#endif