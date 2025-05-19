# Lavandería CleanFast

La empresa **CleanFast** ofrece un servicio de **lavandería automática** con autoservicio. Es un pequeño local de barrio que dispone de **4 lavadoras**, donde los clientes pueden venir a lavar su ropa sin asistencia. Cada lavado dura un tiempo aleatorio entre **5 y 10 segundos**, simulando el tiempo real que tomaría un ciclo corto.

El lavado cuesta **3 euros**, pero **el cliente no paga ni se tiene en cuenta como atendido hasta que accede efectivamente a una lavadora.** Si no hay ninguna lavadora disponible, el cliente debe esperar en la cola hasta que alguna quede libre.

Queremos desarrollar una **simulación concurrente** de esta lavandería, en la que se gestionen correctamente los recursos disponibles (las lavadoras) y el flujo de clientes de sforma realista.

---

**El programa debe mostrar un menú con tres opciones:**

1. **Simular llegada de clientes**: Esta opción lanza varios hilos, cada uno representando un cliente que llega a la lavandería.
- Si hay lavadoras disponibles, el cliente accede, paga, y comienza su lavado (tiempo aleatorio entre 5 y 10 segundos).
- Si todas las lavadoras están ocupadas, el cliente espera en una cola hasta
que una quede libre

2. **Mostrar estado actual de la lavandería**: Esta opción muestra.
- El número total de clientes atendidos (solo se cuenta cuando empieza el lavado).
- La ganancia total (clientes atendidos × 3 €).
- El número de lavadoras disponibles.
- El número de lavadoras en uso.

3. **Cerrar la lavandería**: Al seleccionar esta opción.
- No se aceptan más clientes nuevos.
- El programa debe esperar a que todos los clientes que estaban esperando o lavando terminen.
- Cuando todas las lavadoras estén libres, se debe finalizar correctamente la ejecución.