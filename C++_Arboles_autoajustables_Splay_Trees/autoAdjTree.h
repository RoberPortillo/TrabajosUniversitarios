//Roberto Portillo
#include <fstream>
using namespace std;
template <class T>
class autoAdjTree {

private:
	/*
	/ Nodo que almacena un elemento de tipo T
	/ y tres punteros a sus hijos derecho e izquierdo y a su nodo padre.
	/ Además la estructura contiene una constructora para
	/ facilitar la creación de nodos más adelante.
	*/
	struct node;
	typedef node* link;
	struct node {
		node(link const& l, T const& e, link const& r, link const& p) : left(l), elem(e), right(r), parent(p) {};
		T elem;
		link right, left, parent;
	};

	//Constructora raiz
	autoAdjTree(link const& r) : raiz(r) {};

	link raiz;

public:

	//Constructora de árbol vacio
	autoAdjTree() : raiz(nullptr) {} ;

	//Liberar memoria
	void libera() {
		if (this->raiz != nullptr) {
			this->left().libera();
			this->right().libera();
			delete this->raiz;
		}
	}

	//Comprueba que el árbol sea vacio
	bool empty() const {
		return this->raiz == nullptr;
	}

	//Devuelve el elemento raiz
	T const& root() const {
		if (!this->empty())
			return this->raiz->elem;
		else return 0;
	}

	//Consultar el hijo izquierdo
	autoAdjTree<T> left() const {
		if (this->empty())
			return nullptr;
		else
			return autoAdjTree<T>(this->raiz->left);
	}
	
	//Consultar el hijo derecho
	autoAdjTree<T> right() const {
		if (this->empty())
			return nullptr;
		else
			return autoAdjTree<T>(this->raiz->right);
	}

	//Buscar un elemento
	bool buscar(T const& e) {
		return this->buscar(this->raiz, e);
	}


	//Insertar un elemento
	bool insert(T const& e) {
		link padre = nullptr;
		return this->insertar(this->raiz, e, padre);
	}

	//Borrar un elemento
	bool borrar(T const& e) {
		return this->borrar(this->raiz, e);
	}

	//Mostrar el arbol por pantalla
	void mostrar() {
		mostrar(this->raiz, 0);
	}
private:

	//Implementacion de buscar
	bool buscar(link & raiz, T const& e) {
		//Hemos encontrado el elemento, lo flotamos
		if (raiz->elem == e) {
			flotar(raiz);
			return true;
		}
		//El elemento es menor que la raiz ==> busca en el hijo izquierdo, si es nulo acaba la recursion
		if (raiz->elem > e) {
			if (raiz->left == nullptr)
				return false;
			else
				return buscar(raiz->left, e);
		}
		//El elemento es mayor que la raiz ==> busca en el hijo derecho, si es nulo acaba la recursion
		if (raiz->elem < e) {
			if (raiz->right == nullptr)
				return false;
			else
				return buscar(raiz->right, e);
		}
		//Si llega hasta aqui ha habido algun error ==> acaba la recursion
		return false;
	}

	//Implementacion de insertar
	bool insertar(link & raiz, T const& e, link & parent) {
		//Llega a un arbol vacio ==> inserta el elemento aqui y lo flota
		if (raiz == nullptr) {
			raiz = new node(nullptr, e, nullptr, parent);
			flotar(raiz);
			return true;
		}
		//El elemento ya existe ==> flota el nodo y acaba la recursion
		if (raiz->elem == e) {
			flotar(raiz);
			return false;
		}
		//El elemento es menor que la raiz ==> inserta en el arbol izquierdo
		if (raiz->elem > e)
			return insertar(raiz->left, e, raiz);
		//El elemento es mayor que la raiz ==> inserta en el arbol derecho
		if (raiz->elem < e)
			return insertar(raiz->right, e, raiz);
		//En caso de llegar aquí ha habido algún error ==> acaba la recursion
		return false;
	}

	//Implementacion de borrar
	bool borrar(link & raiz, T const& e) {
		int min;
		//El elemento es menor que la raiz ==> borra en el hijo izquierdo, si es nulo acaba la recursion
		if (raiz->elem > e) {
			if (raiz->left == nullptr)
				return false;
			else
				return borrar(raiz->left, e);
		}
		//El elemento es mayor que la raiz ==> borra en el hijo derecho, si es nulo acaba la recursion
		if (raiz->elem < e) {
			if (raiz->right == nullptr)
				return false;
			else
				return borrar(raiz->right, e);
		}
		//El elemento es igual a la raiz ==> procede a borrarlo y flotar al padre
		if (raiz->elem == e) {
			link padre;
			//El subarbol derecho esta vacio ==> el hijo izquierdo se convierte en raiz
			if (raiz->right == nullptr) {
				link aux = raiz;
				padre = raiz->parent;
				if(raiz->left != nullptr)
					raiz->left->parent = raiz->parent;
				raiz = raiz->left;
				delete aux;
			}
			/*
			/El subarbol derecho no esta vacio ==> llamada a borraMin con el
			/hijo derecho, el elemento minimo pasa a suplantar a la raiz
			*/
			else {
				padre = borraMin(min, raiz->right);
				raiz->elem = min;
			}
			flotar(padre);
			return true;
		}

		//En caso de llegar aqui se ha producido algun error ==> acaba la recursion
		return false;
	}

	//Borrar el elemnto minimo y lo devuelve, tambien devuelve un enlace al nodo padre del elemento borrado
	link borraMin(T & min, link & raiz) {
		//El hijo izquierdo es nulo ==> devuelve la raiz como minimo y la suplanta por el hijo derecho
		if (raiz->left == nullptr) {
			min = raiz->elem;
			link padre = raiz->parent, aux = raiz;
			if(raiz->right != nullptr)
				raiz->right->parent = raiz->parent;
			raiz = raiz->right;
			delete aux;
			return padre;
		}
		//El hijo izquierdo no es nulo ==> llamada a busca min con el hijo izquierdo
		else
			return borraMin(min, raiz->left);
	}

	//Implementacion de la rotacion derecha
	void derechaRotar(link x) {
		//Y hace de hijo derecho participe en la rotacion
		link y = x->right;
		//Si existe el hijo derecho intercambiamos los hijos 
		if (y != nullptr) {
			x->right = y->left;
			if (y->left != nullptr)
				y->left->parent = x;
			y->parent = x->parent;
			y->left = x;
		}
		//Si x era la raiz ahora lo es y
		if (x->parent == nullptr)
			raiz = y;
		//Si x era hijo derecho ahora lo es y
		else if (x->parent->left == x)
			x->parent->left = y;
		//Si x era hijo izquierdo ahora lo es x
		else
			x->parent->right = y;

		x->parent = y;
	}
	
	//Implementacion de la rotacion izquierda
	void izquierdaRotar(link x) {
		//Y hace de hijo izquierdo participe en la rotacion
		link y = x->left;
		//Si existe el hijo derecho intercambiamos los hijos 
		if (y != nullptr) {
			x->left = y->right;
			if (y->right != nullptr)
				y->right->parent = x;
			y->parent = x->parent;
			y->right = x;
		}
		//Si x era la raiz ahora lo es y
		if (x->parent == nullptr)
			raiz = y;
		//Si x era hijo derecho ahora lo es y
		else if (x->parent->right == x)
			x->parent->right = y;
		//Si x era hijo izquierdo ahora lo es x
		else
			x->parent->left = y;

		x->parent = y;
	}


	void flotar(link x) {
		//En caso de que el nodo no tenga padre acaba la recursion
		if (x->parent != nullptr) {
			//Caso en el que no hay abuelo
			if (x->parent->parent == nullptr) {
				if (x->parent->right != x)
					izquierdaRotar(x->parent);
				else
					derechaRotar(x->parent);
			}
			//Caso LL ==> Rota a la izquierda el abuelo y luego el padre
			else if ((x->parent->parent->left == x->parent) && (x->parent->left == x)) {
				izquierdaRotar(x->parent->parent);
				izquierdaRotar(x->parent);
				flotar(x);
			}
			//Caso RR ==> Rota a la derecha el abuelo y luego el padre
			else if ((x->parent->parent->right == x->parent) && (x->parent->right == x)) {
				derechaRotar(x->parent->parent);
				derechaRotar(x->parent);
				flotar(x);
			}
			//Caso LR ==> Rota a la derecha el padre y luego a la izquierda el abuelo
			else if ((x->parent->parent->right == x->parent) && (x->parent->left == x)) {
				izquierdaRotar(x->parent);
				derechaRotar(x->parent);
				flotar(x);
			}
			//Caso RL ==> Rota a la izquierda el padre y luego a la derecha el abuelo
			else {
				derechaRotar(x->parent);
				izquierdaRotar(x->parent);
				flotar(x);
			}
		}
	}

	//Implementacion de mostrar
	void mostrar(link const& raiz, int nivel) {
		if (raiz == nullptr)
			return;
		mostrar(raiz->right, nivel + 1);
		for (int i = 0; i < nivel; i++) {
			cout << "---";
		}
		cout << raiz->elem << "\n";
		mostrar(raiz->left, nivel + 1);
	}
};