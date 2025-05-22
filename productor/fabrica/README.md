# 🏭 Fábrica de Piezas

---

## 🧪 Contexto

Estamos en una **fábrica de piezas mecánicas** con un sistema automatizado que simula un flujo de producción real.  
El sistema cuenta con **tres máquinas automáticas** que producen piezas, una **cinta transportadora circular** y una **máquina empaquetadora** que recoge y embala las piezas.

---

## ⚙️ Estructura del Sistema

- **Máquinas productoras (3 hilos)**:
  - Generan piezas automáticamente cada **1 a 3 segundos**.
  - Colocan la pieza en la cinta **si hay espacio disponible**.
  - Si la cinta está llena, deben **esperar**.

- **Máquina empaquetadora (1 hilo)**:
  - Retira una pieza cada **2 segundos**.
  - Si no hay piezas en la cinta, **espera** hasta que haya al menos una.

---

## 📦 Cinta Transportadora

- La cinta tiene una **capacidad máxima de 5 piezas**.
- Opera como una **estructura circular sincronizada**.
- Cada vez que se **agregue** o **retire** una pieza, se debe **mostrar el estado actual** de la cinta.

---

## ⏳ Dinámica de Funcionamiento

1. Las máquinas productoras generan piezas cada `1–3` segundos.
2. La máquina empaquetadora retira una pieza cada `2` segundos.
3. El sistema **sincroniza las operaciones** para evitar condiciones de carrera o bloqueos.

---

## 🛑 Finalización del Proceso

- El sistema finaliza cuando se hayan **fabricado exactamente 50 piezas**.
- La cinta debe estar **vacía al final del proceso**.
- Una vez todos los hilos han terminado su trabajo, se debe mostrar el mensaje:

`Fábrica cerrada`
