%PARAMETROS
% Numero de objetos que podemos comprar
par int: nObjetos;
%Presupuesto
par 0..30: P;
%Lista de precios
array [1..nObjetos] of par int: precios;
%Lista de superficies
array [1..nObjetos] of par int: superficies;

%Comprobaciones de parametros
constraint assert ((0 <= nObjetos /\ nObjetos <= 30), "Numero de objetos entre [0,30]");

%VARIABLES
%0 = no comprar el objeto / 1 = comprar el objeto
array [1..nObjetos] of var 0..1: comprados;

%%RESTRICCIONES

%No exceder el presupuesto
constraint (sum (i in (1..nObjetos) where comprados[i] = 1) (precios[i])) <= P;

%Maximizar superficie
solve maximize (sum (i in (1..nObjetos) where comprados[i] = 1) (superficies[i]));

output ["\(comprados[i])\t"| i in 1..nObjetos];

output ["\(sum (i in (1..nObjetos) where comprados[i] = 1) (superficies[i]))"];
