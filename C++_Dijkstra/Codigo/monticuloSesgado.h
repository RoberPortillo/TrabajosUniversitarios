// ROBERTO PORTILLO TORRES

#ifndef MONTSESGADO__H
#define MONTSESGADO__H

#include <utility>

using namespace std;


// Tipo utilizado para definir una arista <destino, valor>
typedef pair<int, double> par;

const int MAX_NODOS = 20000;

/*
* DESCRIPCION: Monticulo de pares <int, double> de minimos.
*              Utiliza el valor real como clave, las claves menores seran mas cercanas a la raiz.
*              Metodos de uso publico:
*                   - monticuloSesgado() -> constructora
*                   - libera() -> destructora de memoria
*                   - vacio() -> true si el monticulo es vacio flase en caso contrario
*                   - minimo() -> devuelve el elemento minimo
*                   - eliminarMinimo() -> elimina el elemento minimo
*                   - insertar(par e) -> inserta un nuevo elemento
*                   - decrecerClave(int i, double valor) -> decrece la clave del elemento i
*/
class monticuloSesgado {

private:
	/*
	/ Nodo que almacena un elemento de tipo par<int, double>
	/ y dos punteros a sus hijos derecho e izquierdo.
	/ Ademas la estructura contiene una constructora para
	/ facilitar la creacion de nodos mas adelante.
	*/
	struct node;
	typedef node* link;
	struct node {
		node(link const& l, par const& e, link const& r, link const& p) : left(l), elem(e), right(r), padre(p) {};
		par elem;
		link right, left, padre;
	};

    // Puntero al nodo raiz
	link raiz;

    /*
    * Array de punteros a nodos, la posicion i contiene un puntero al nodo i.
    * Cuando se inserta un nodo se añade un puntero a la lista.
    * Apoyarnos en esta lista nos permite dejar el coste de decrecerClave en O(log N)
    */
    link nodos[MAX_NODOS];

public:

	// Constructora de monticulo vacio
    // COSTE: O(1)
	monticuloSesgado() : raiz(nullptr) {} ;

	// Liberar memoria
    // COSTE: O(N)
	void libera() {
        libera(this->raiz);
	}

	// Comprueba que el monticulo sea vacio
    // COSTE: O(1)
	bool vacio() const {
		return this->raiz == nullptr;
	}

	// Elemento minimo: si el monticulo no es vacio devuelve el elemento raiz, si es vacio devuelve (-1,-1)
	// COSTE: O(1)
    const par minimo() const {

		if (!this->vacio())
			return this->raiz->elem;

        return par(-1,-1);
	}

	// Borrar el elemento minimo: borra la raiz y realiza la union de sus hijos
    // COSTE: O(log N)
	void eliminarMinimo() {
        
        link aux;
        if(this->raiz != nullptr){

            aux = this->unionSesgada(this->raiz->left, this->raiz->right);
            delete this->raiz;
            if(aux != nullptr)
                aux->padre = nullptr;
            this->raiz = aux;
        }
	}

    // Insertar un elemento: realiza la union de la raiz y el elemento a insertar (parametro)
    // COSTE: O(log N)
	void insertar(par const& e) {

        link nuevo = new node(nullptr, e, nullptr, nullptr);
        this->raiz = this->unionSesgada(this->raiz, nuevo);
        nodos[e.first] = nuevo;
	}

    // Decrecer la prioridad de un elemento y flotarlo hasta que sea mayor que el padre o raiz
    // COSTE: O(log N)
    void decrecerClave(int i, double valor){

        if(valor < nodos[i]->elem.second){

            this->nodos[i]->elem = par(i, valor);
            flotar(nodos[i]);
        }
    }

private:

    // Operacion de unión de monticulo sesgado de minimos
    // COSTE: O(log N)
	link unionSesgada(link r1, link r2){

        link aux;
        //Si un monticulo es vacio devolvemos el contrario
        if(r1 == nullptr)
            return r2;
        if(r2 == nullptr)
            return r1;
        
        //Identificamos la raiz menor, comparando niveles de prioridad (segundo en la tupla)
        if(r1->elem.second <= r2->elem.second){
            //Intercambiamos hizo izquierdo y derecho
            aux = r1->right;
            r1->right = r1->left;
            //Asignamos el nodo padre
            r2->padre = r1;
            //Asignamos el valor de la recursión al hijo izquierdo
            r1->left = unionSesgada(aux, r2);
            //Devolvemos la raiz menor, la otra esta insertada en niveles inferiores
            return r1;
        }
        else{
            //Accion simetrica si r2 es menor
            aux = r2->right;
            r2->right = r2->left;
            r1->padre = r2;
            r2->left = unionSesgada(aux, r1);
            return r2;
        }

        return nullptr;
    }

    // Flotar un elemento hasta que sea menos que su padre
    // COSTE: O(log N)
    void flotar(link r){

        link p = r->padre;
        //Mientras el padre no sea nulo y sae mayor que el elemento los intercambiamos
        while(p != nullptr && p->elem.second > r->elem.second){

            //r se identifica como hijo derecho
            if(p->right == r){
                //Se hace padre de su hermano si este no es nulo
                if(p->left != nullptr)
                    p->left->padre = r;
                //Intercambia punteros con su padre
                swap(p->left, r->left);
                p->right = r->right;
                if(p->left != nullptr)
                    p->left->padre = p;
                if(p->right != nullptr)
                    p->right->padre = p;
                r->right = p;
            }
            //Codigo simetrico para el caso contrario
            else{
                if(p->right != nullptr)
                    p->right->padre = r;
                swap(p->right, r->right);
                p->left = r->left;
                if(p->left != nullptr)
                    p->left->padre = p;
                if(p->right != nullptr)
                    p->right->padre = p;
                r->left = p;
            }
            //r se actualiza como hijo de su nodo abuelo(padre del padre)
            if(p->padre != nullptr){
                if(p->padre->right == p)
                    p->padre->right = r;
                else
                    p->padre->left = r;
            }
            
            //Actualizamos los nodos padre
            r->padre = p->padre;
            p->padre = r;
            //Actualizamos p para la siguiente iteracion
            p = r->padre;
        }

        //Si hemos llegado a la raiz actualizamos el puntero raiz
        if(p == nullptr)
            this->raiz = r;
    }

    // Libera la memoria dinamica
    // COSTE: O(N)
    void libera(link r){

        if(r != nullptr){
            libera(r->left);
            libera(r->right);
            delete r;
        }
    }
};
#endif