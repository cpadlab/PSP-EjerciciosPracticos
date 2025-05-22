# 🤖 Simulación Cliente-Servidor de Farmacia

---

## 🧪 Contexto

**FarmaciaViva** ha dado el paso hacia la digitalización y necesita una arquitectura básica para simular su nuevo flujo de ventas.  
El sistema está compuesto por un **servidor de stock** y **clientes concurrentes** que simulan compras.

---

## ✅ Requisitos del Sistema

### 1. 🖥️ Servidor

- El servidor recibe **peticiones de compra**.
  - Si hay stock suficiente, **descuenta la cantidad** y responde con un mensaje de éxito.
  - Si **no hay stock suficiente**, responde con un **mensaje de error adecuado**.

> El servidor es responsable de mantener la integridad del stock ante múltiples accesos concurrentes.

---

### 2. 🧴 Productos

- Solo hay **3 productos disponibles**:
  - Paracetamol
  - Ibuprofeno
  - Vitamina C

- Cada producto tiene un **stock inicial** (ejemplo: 10 unidades).
- El stock se gestiona **en memoria** mediante el uso de variables.

> No se persiste en disco. Es una simulación temporal en tiempo de ejecución.

---

### 3. 🧵 Simulación de Clientes

- Se deben crear **varios hilos cliente** (`threads`) que simulan peticiones concurrentes al servidor.
- Cada hilo cliente:
  - Solicita un **producto aleatorio**.
  - Solicita una **cantidad aleatoria entre 1 y 2 unidades**.

- El servidor debe:
  - Atender las peticiones **de forma simultánea**.
  - Gestionar los accesos al stock con técnicas de **sincronización de hilos**, evitando condiciones de carrera.
