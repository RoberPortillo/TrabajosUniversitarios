#ifndef _POLINOMIO
#define _POLINOMIO

#include "polinomio.h"

polinomio::polinomio(int coeficiente,int exponente){
	this->coeficiente = coeficiente;
	this->exponente = exponente;
}

void polinomio::read(std::istream & i = std::cin) {
	int coeficiente, exponente;
	i >> coeficiente >> exponente;
	this->coeficiente = coeficiente;
	this->exponente = exponente;
}
int polinomio::getCoeficiente()const{
	return this->coeficiente;
}

int polinomio::getExponente()const{
	return this->exponente;
}




#endif