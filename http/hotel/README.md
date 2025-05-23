# 🏨 Hotel PelHilos a la Mar — Servidor HTTP Concurrente con Cifrado AES

---

## 🧪 Contexto

Se requiere implementar un **servidor HTTP en Java** para gestionar **reservas concurrentes** en un hotel.  
Cada recepción del hotel accede a una **página web alojada por el servidor**, donde el personal puede hacer reservas de habitaciones para un día específico.

---

## 🌐 Funcionalidad Web

Desde el navegador, el formulario permite:

1. Seleccionar el **día de la semana** (lunes a domingo) mediante un **desplegable**.
2. Introducir el **número de habitaciones** a reservar.

---

## 🗃 Estructura de Archivos

- En la carpeta raíz del proyecto existen **siete archivos de texto cifrados**:
  - `lunes.txt`, `martes.txt`, ..., `domingo.txt`
- Cada archivo contiene **únicamente** el **número total acumulado de reservas** para ese día.
  - Ejemplo: si un empleado reserva `5` habitaciones para el lunes → el contenido cifrado del archivo `lunes.txt` representará `5`.
  - Si otro reserva `7` más → el contenido cifrado se actualizará a `12`.

> ⚠ Los archivos están cifrados con **AES**, por lo que su contenido **no es legible directamente**. Para depuración, el valor **descifrado se debe imprimir en consola** tras cada actualización.

---

## 🧩 Componentes base del proyecto

Ya se proporcionan:

- `Paginas.java` con las plantillas HTML.
- `html_reservas`: Página principal de reservas.
- `html_noEncontrado`: Página de error para rutas no válidas.

---

## 🛠 Requisitos Técnicos

### 1. 🧭 Rutas del Servidor

- `GET /`: Retorna la página `html_reservas`.
- `POST /`: Gestiona la reserva.
- Cualquier otra ruta: Retorna `html_noEncontrado` con estado HTTP `404`.

---

### 2. 🔐 Gestión de Archivos Cifrados

Para cada solicitud de reserva:

- Se recibe el cuerpo del `POST` con el formato `dia=x&cantidad=y`.
- Se realiza el siguiente flujo:

  1. **Descifrar** el fichero `x.txt`.
  2. **Leer y actualizar** el valor de reservas.
  3. **Volver a cifrar** el contenido actualizado.
  4. **Sobrescribir** el archivo cifrado.

- Imprimir por consola el valor descifrado actualizado para verificación.

---

### 3. 🧵 Concurrencia y Sincronización

- Cada petición es atendida por su **propio hilo**.
- La sección crítica (acceso a los archivos) debe estar protegida con `synchronized`:
  - `descifrar → leer/actualizar → cifrar → escribir`
- Mientras un hilo actualiza un archivo, **ningún otro puede acceder a ese mismo fichero**.

---

## 📌 Notas Importantes

- Usar la clase `Cipher` de Java para cifrado AES.
- Asegurarse de manejar correctamente excepciones y errores de lectura/escritura.
- El sistema debe escalar correctamente ante múltiples peticiones simultáneas.
