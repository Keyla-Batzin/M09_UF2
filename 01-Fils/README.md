Keyla Batzin
12/12/24

# Introducció als Fils i la Classe Thread

En programació, un **fil** (o "thread" en anglès) és una part d’un programa que pot executar-se de manera independent al mateix temps que altres fils. Això permet fer diverses tasques alhora dins d’un mateix programa, cosa que s’anomena **execució concurrent** o **multifil**.

## Per què utilitzar fils?

Els fils són útils per a:
- Fer que el programa sigui més ràpid i eficient.
- Gestionar diverses tasques alhora, com ara:
  - Càlculs intensius en segon pla.
  - Mostrar informació a la pantalla mentre es processen dades.
  - Treballar amb xarxes o fitxers sense bloquejar el programa principal.

## La Classe `Thread` a Java

Java ofereix la classe `Thread` per crear i gestionar fils. Hi ha dues formes principals per treballar amb fils:

### 1. **Heretar de la Classe `Thread`**
   - Es crea una nova classe que **extén** `Thread`.
   - Es sobreescriu el mètode `run()`, que conté el codi que ha d'executar el fil.
   - Es crea un objecte de la nova classe i es crida el mètode `start()`.

### 2. **Implementar la Interfície `Runnable`**
   - Es crea una classe que implementa la interfície `Runnable`.
   - Es defineix el codi dins el mètode `run()`.
   - L'objecte de tipus `Runnable` es passa a un objecte de la classe `Thread`.
   - Es crida el mètode `start()` del fil.

## Comportament 1

### Codi:
```java
public class Fil extends Thread {
    private String nom; // Nombre del hilo

    public Fil(String nom) {
        this.nom = nom; // Inicializa el nombre
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 9; i++) {
                System.out.println(nom + " " + i);
                Thread.sleep((int) (Math.random() * 200) + 100); // Pausa entre 100ms y 300ms
            }
            System.out.println("Termina el fil " + nom); // Mensaje al finalizar
        } catch (InterruptedException e) {
            System.out.println("El fil " + nom + " ha estat interromput.");
        }
    }
}
```
```java

```


### Execució:

![alt text](image.png)

### Explicació

El programa comença executant el fil principal (main), que inicialitza els fils "Juan" i "Pepe" i acaba ràpidament mostrant "Termina thread main". Després, "Juan" i "Pepe" van executant els seus missatges, intercalant-se de manera més o menys equilibrada, però no sempre en l’ordre exacte esperat. 

Això passa perquè el sistema decideix quin fil executar en cada moment, i l’ordre pot canviar a cada execució. Finalment, quan cadascun acaba de mostrar els seus missatges fins al 9, es mostra el text Termina el fil Juan i Termina el fil Pepe, indicant que han acabat les seves tasques.

## Comportament 2


