# Fábrica de Piezas

Estamos en una fábrica que produce piezas mecánicas. La fábrica cuenta con **tres máquinas automáticas** que generan piezas y las colocan en una **cinta transportadora** circular para que luego una **maquina empaquetadora** las recoja y las embale.

La cinta tiene una **capacidad máxima de 5 piezas**, por lo que sí está llena, las máquinas deben esperar a que se libere espacio. Por otro lado, si la maquina empaquetadora intenta recoger una pieza y no hay ninguna disponible, también deberá esperar a que las máquinas depositen una.

**Dinámica**:
1. Las máquinas generan piezas cada 1–3 s y las ponen en la cinta si hay espacio; si está llena, esperan.
2. El empaquetador retira una pieza cada 2 s; si no hay piezas, espera.
3. Mostrar el estado de la cinta cada vez que se ponga o retire una pieza.

**¿Cuándo termina?**: Cuando se fabriquen 50 piezas. Pero no pueden quedarse piezas en la cinta.

Mostrar el mensaje “Fábrica cerrada” cuando todos los hilos terminen.
