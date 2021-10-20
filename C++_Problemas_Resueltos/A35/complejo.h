#ifndef COMPLEJO_H
#define COMPLEJO_H

#include <cmath>

template <class T>

class complejo{

private:

	T real;
	T im;

	void copia(complejo<T> const& other);

public:

	complejo();//constructor defecto
	complejo(T a, T b); //constructor
	complejo(complejo<T> const& other); //constructor por copia
	void operator+(complejo<T> const& other);
	void operator*(complejo<T> const& other);
	T mod();

};

template <class T>
complejo<T>::complejo() :real(0), im(0) {};

template <class T>
complejo<T>::complejo(T a, T b) :real(a), im(b){};

template <class T>
complejo<T>::complejo(complejo<T> const& other){

	copia(other);
}

template <class T>
void complejo<T>::copia(complejo<T> const& other){

	this->real = other.real;
	this->im = other.im;
}

template <class T>
void complejo<T>::operator+(complejo<T> const& other){

	this->real = this->real + other.real;
	this->im = this->im + other.im;
}

template <class T>
void complejo<T>::operator*(complejo<T> const& other){

	this->real = (this->real*other.real) - (this->im*other.im);
	this->im = (this->real*other.im) + (this->im*other.real);
}

template <class T>
T complejo<T>::mod(){

	T a = this->real * this->real;
	T b = this->im * this->im;
	a += b;
	a = sqrt(a);
	return a;
}

#endif