# 🧼 Lavandería CleanFast — Simulación Concurrente

---

## 🧪 Contexto

**CleanFast** es una lavandería de autoservicio con **4 lavadoras disponibles**.  
Los clientes pueden acudir de forma autónoma para lavar su ropa. Cada lavado tiene una duración aleatoria de entre `5` y `10` segundos, simulando un **ciclo corto de lavado real**.

El servicio tiene un coste de `3 €`, **pero solo se cobra cuando el cliente accede efectivamente a una lavadora**.  
Si todas las lavadoras están ocupadas, el cliente debe esperar su turno en una **cola de espera**.

---

## ⚙️ Objetivo de la Simulación

Desarrollar una **simulación concurrente** que gestione correctamente:
- El número limitado de lavadoras.
- La llegada y espera de clientes.
- El ciclo de lavado.
- El cierre ordenado del sistema.

---

## 🧾 Menú del Programa

### 1. 🚶 Simular llegada de clientes

- Se lanzan múltiples **hilos**, cada uno representando un **cliente**.
- Comportamiento del cliente:
  - Si hay lavadoras disponibles:
    - Accede, **paga 3 €**, y comienza su lavado (`5–10` s aleatorio).
  - Si no hay lavadoras disponibles:
    - **Espera en cola** hasta que una lavadora esté libre.

---

### 2. 📊 Mostrar estado actual de la lavandería

Muestra en consola:

- Número **total de clientes atendidos** (solo se cuentan cuando inician el lavado).
- **Ganancia total acumulada** (`clientes × 3 €`).
- **Número de lavadoras disponibles**.
- **Número de lavadoras actualmente en uso**.

---

### 3. 🔒 Cerrar la lavandería

Al seleccionar esta opción:

- Se **deja de aceptar** la llegada de nuevos clientes.
- El sistema espera a que:
  - Todos los clientes **en cola** sean atendidos.
  - Todos los lavados en proceso **finalicen correctamente**.
- Una vez todas las lavadoras estén libres, mostrar mensaje de cierre:

`Lavandería cerrada.`
