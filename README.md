# CenaDeFilosofos
Algoritmo de la Cena de Filosofos

Este algoritmo se soluciona de la siguiente manera:

##Colas de tenedores
Cuando un filósofo quiere comer se pone en la cola de los dos tenedores que necesita. Cuando un tenedor está libre lo toma. Cuando toma los dos tenedores, come y deja libre los tenedores.
Visto desde el otro lado, cada tenedor sólo puede tener dos filósofos en cola, siempre los mismos.
Esto crea el problema comentado de que si todos quieren comer a la vez y todos empiezan tomando el tenedor de su derecha se bloquea el sistema (deadlock).

##Resolución de conflictos en colas de tenedores
Cada vez que un filósofo tiene un tenedor espera un tiempo aleatorio para conseguir el segundo tenedor. Si en ese tiempo no queda libre el segundo tenedor, suelta el que tiene y vuelve a ponerse en cola para sus dos tenedores.
Si un filósofo A suelta un tenedor (porque ha comido o porque ha esperado demasiado tiempo con el tenedor en la mano) pero todavía desea comer, vuelve a ponerse en cola para ese tenedor. Si el filósofo adyacente B está ya en esa cola de tenedor (tiene hambre) lo toma y si no vuelve a cogerlo A.
Es importante que el tiempo de espera sea aleatorio o se mantendrá el bloqueo del sistema.
