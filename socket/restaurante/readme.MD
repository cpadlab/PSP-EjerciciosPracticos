# Gestión de Reservas de un Restaurante Exclusivo

El restaurante **“Exclusivo 25”** tiene una capacidad máxima de **25 comensales por día**.  
El restaurante únicamente abre **sábados y domingos al medio día**, y las reservas para esos días están disponibles **únicamente desde la app de reservas durante esa misma semana**.

El objetivo de este proyecto es **crear una aplicación cliente-servidor** para gestionar las reservas del restaurante de manera eficiente, utilizando **hilos, sincronización** y una arquitectura **cliente-servidor**.

---

## Requisitos del Proyecto

### 1. Estructura de Archivos

El proyecto deberá tener al menos los siguientes 3 ficheros principales:

- `Servidor.java`
- `Cliente.java`
- `SimuladorClientes.java`

---

### 2. Servidor.java

- Gestionará las reservas.
- Cada cliente tendrá un hilo servidor que se encargará de procesar su reserva.
- El servidor preguntará al cliente:
  1. ¿Para qué día quiere reservar? (sábado o domingo)
  2. ¿Para cuántos comensales?

- Verificará si hay plazas disponibles **de manera sincronizada** para evitar conflictos de concurrencia.
- Mostrará por consola:
  - Qué cliente realizó una reserva.
  - Para qué día y cuántos comensales.
  - Plazas disponibles de ambos días tras la reserva.
- Si **no hay plazas suficientes**, mostrará un mensaje indicando que **no fue posible realizar la reserva** para el cliente X, para el día X, para X comensales.

---

### 3. Cliente.java

Cada hilo cliente responderá al servidor:

- Día para reservar:  
  Generará un número aleatorio entre **1 y 2** (1 = sábado, 2 = domingo).  
  *No se mostrará por pantalla.*

- Número de comensales:  
  Generará un número aleatorio entre **2 y 6**.  
  *No se mostrará por pantalla.*

- Luego, el cliente deberá **mostrar por pantalla**:
  - Su nombre.
  - Día solicitado.
  - Número de comensales solicitados.

- Esperará a saber si la reserva fue exitosa o si no hay suficientes espacios, y después **terminará su ejecución**.

---

### 4. SimuladorClientes.java

- Simulará el comportamiento concurrente de los clientes.
- Mostrará un **menú interactivo** con dos opciones:
  1. **Simular peticiones de clientes**:  
     El usuario indicará cuántos clientes se lanzan a realizar reservas al mismo tiempo.
  2. **Terminar el programa**

- El menú se volverá a mostrar **cuando todos los hilos clientes lanzados previamente hayan sido gestionados**.