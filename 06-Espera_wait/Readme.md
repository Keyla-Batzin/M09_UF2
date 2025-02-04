# **Preguntes Teòriques**

## **1. Per què s'atura l'execució al cap d'un temps?**
L'execució del programa s'atura perquè els fils d'`Assistent` poden ser interromputs externament o poden trobar-se en una situació en què cap d'ells pugui realitzar accions significatives. Això pot passar si:
- Tots els assistents que podrien fer una reserva ja ho han fet i cap vol cancel·lar.
- Si el programa es deté manualment o si s'executa en un entorn amb una limitació de temps d'execució per fils.

## **2. Què passaria si canviem les probabilitats?**
### **Cas 1: 70% ferReserva - 30% cancelaReserva**

**Modificació del codi:**
```java
@Override
public void run() {
    while (true) {
        try {
            if (random.nextInt(100) < 70) { // 70% de probabilitat
                event.ferReserva(this);
            } else {
                event.cancelaReserva(this);
            }
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            System.out.println(nom + " ha estat interromput.");
            break;
        }
    }
}
```

**Sortida esperada:**
Amb aquest canvi, és probable que l'esdeveniment es quedi sense places disponibles gairebé sempre, i molts assistents quedaran esperant poder fer una reserva:
```
Assistent-0 ha fet una reserva. Places disponibles: 4
Assistent-1 ha fet una reserva. Places disponibles: 3
Assistent-2 ha fet una reserva. Places disponibles: 2
Assistent-3 ha fet una reserva. Places disponibles: 1
Assistent-4 ha fet una reserva. Places disponibles: 0
Assistent-5 no ha pogut fer una reserva. Places disponibles: 0
...
```

### **Cas 2: 30% ferReserva - 70% cancelaReserva**

**Modificació del codi:**
```java
@Override
public void run() {
    while (true) {
        try {
            if (random.nextInt(100) < 30) { // 30% de probabilitat
                event.ferReserva(this);
            } else {
                event.cancelaReserva(this);
            }
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            System.out.println(nom + " ha estat interromput.");
            break;
        }
    }
}
```

**Sortida esperada:**
Amb aquest canvi, les places disponibles no es quedaran plenes gairebé mai, ja que hi haurà més cancel·lacions que reserves:
```
Assistent-0 ha fet una reserva. Places disponibles: 4
Assistent-1 ha fet una reserva. Places disponibles: 3
Assistent-2 ha cancel·lat la seva reserva. Places disponibles: 4
Assistent-3 ha cancel·lat una reserva inexistent. Places disponibles: 4
...
```

## **3. Per què fa falta la llista i no valdria només amb una variable sencera de reserves?**
Una variable sencera només indicaria el nombre de places ocupades, però no permetria identificar qui ha fet la reserva.

La llista és necessària per:
- Saber quins assistents han reservat i permetre'ls cancel·lar correctament.
- Evitar cancel·lacions incorrectes, ja que si no es guarda una llista, un assistent podria cancel·lar una reserva que no ha fet.
- Gestionar correctament els fils i assegurar que només els assistents que han reservat puguin alliberar places.

Sense la llista, el programa tindria un comportament incoherent i podria permetre cancel·lacions errònies.

