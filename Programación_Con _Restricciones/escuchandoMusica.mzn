%Parametros
par 1..50: nCanciones;
par 1..100: tIda;
par 1..100: tVuelta;
array [1..nCanciones] of par int: duraciones;
array [1..nCanciones] of par int: puntuaciones;

%Variables
array [1..nCanciones] of var 0..1: cancIda;
array [1..nCanciones] of var 0..1: cancVuelta;

%Restricciones

%No coger la misca cancion dos veces
constraint forall (i in 1..nCanciones) (cancIda[i] = 0 \/ cancVuelta[i] = 0);

%Predicado que asegura que el tiempo de las canciones dadas encaja con el viaje dado
predicate encajaTiempo(array [1..nCanciones] of var 0..1: canciones, 1..100: tiempo) =
  (sum (i in 1..nCanciones where canciones[i] = 1) (duraciones[i])) = tiempo;

%El tiempo de las canciones para la ida encaja con el tiempo del viaje de ida
constraint encajaTiempo(cancIda, tIda);

%El tiempo de las canciones para la vuelta encaja con el tiempo del viaje de vuelta
constraint encajaTiempo(cancVuelta, tVuelta);

%Funcion que suma la satisfcaccion de las canciones elegidas
function var int: sumaPuntuacion(array [1..nCanciones] of var 0..1: canciones,array [1..nCanciones] of par int: puntuaciones) =
  sum (i in 1..nCanciones where canciones[i] = 1) (puntuaciones[i]);

%Maximizamos las puntuaciones de las canciones elegidas (ERROR DE TIPOS EN LA FUNCION??)
var int: suma;
suma = (sumaPuntuacion(cancIda, puntuaciones) + sumaPuntuacion(cancVuelta, puntuaciones));
%NO ME DEJA USAR FUNCION EN OUTPUT, USAR ESTA VARIABLE ES UNA CHAPUZA? sí, mejor crer una version de la funcion con forma de parametro
solve maximize suma;
output ["\(suma)"]

%solve maximize (sumaPuntuacion(cancIda, puntuaciones) + sumaPuntuacion(cancVuelta, puntuaciones));
%output ["\(sumaPuntuacion(cancIda, puntuaciones) + sumaPuntuacion(cancVuelta, puntuaciones))"]

