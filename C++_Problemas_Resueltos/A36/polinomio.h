#ifndef POLINOMIO_H
#define POLINOMIO_H

#include <iostream>
#include <iomanip>
#include <stdexcept>
#include <vector>
#include <math.h>

class polinomio {

private:

	int coeficiente;
	int exponente;
	

public:
	polinomio(int coeficiente,int exponente);
	
	int getCoeficiente()const;
	int getExponente() const;
	void read(std::istream & i);
	
	
};
#endif