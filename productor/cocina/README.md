# Sincronización en la Cocina: Cocineros y Cuchillos

## Descripción del Problema

En una cocina, 4 cocineros comparten **dos cuchillos** para preparar sus platos.  
Cada cocinero debe coger un cuchillo para trabajar. Como solo hay dos cuchillos disponibles, es necesario **sincronizar su acceso** para evitar conflictos o situaciones donde más de dos cocineros intenten utilizar un cuchillo simultáneamente.

### Reglas de funcionamiento:

- Cuando un cocinero toma un cuchillo, lo utiliza (`sleep`) durante un tiempo aleatorio entre **10 y 20 segundos**.
- Luego lo suelta y se dedica a otra actividad (`sleep`) durante **5 a 10 segundos** antes de volver a intentar coger un cuchillo.
- Cada cocinero repetirá este proceso **cinco veces**.

## Requisitos de Implementación

La solución debe desarrollarse en Java utilizando **hilos (threads)**.

Debe incluir los siguientes archivos:

### 1. `Principal.java`

- Inicializa los cocineros.
- Gestiona y lanza los hilos automáticamente al ejecutarse.

### 2. `Cocinero.java`

- Implementa la lógica de cada cocinero.
- Se debe sobrescribir el método `run()`.

### 3. `Cocina.java`

- Gestiona la sincronización del acceso a los cuchillos.
- Debe contener métodos sincronizados para controlar el acceso.

## Mensajes esperados por consola

Se deben imprimir los siguientes mensajes:

```
Run:

Cocinero1 ha tomado un cuchillo por 1 vez. Cuchillos disponibles: 1
Cocinero 4 ha tomado un cuchillo por 1 vez. Cuchillos disponibles: 0
Cocinero3 está esperando un cuchillo….
Cocinero2 está esperando un cuchillo….
Cocinero4 ha soltado un cuchillo. Cuchillos disponibles: 1
Cocinero3 ha tomado un cuchillo por 1 vez. Cuchillos disponibles 0

[…]

Cocinero3 ha tomado un cuchillo por 5 vez. Cuchillos disponibles: 0
Cocinero1 ha terminado su trabajo.
Cocinero4 ha terminado su trabajo.
Cocinero3 ha soltado un cuchillo. Cuchillos disponibles: 1
Cocinero3 ha terminado su trabajo.

[…]

Todos los cocineros han terminado.

``` 