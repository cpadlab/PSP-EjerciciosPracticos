# 🛡️ Servidor Web Seguro con HTML y Fichero de Stock

---

## 🧪 Contexto

**FarmaciaViva** busca habilitar un sistema de pedidos manuales a través de una **interfaz web segura**.  
El objetivo es que los empleados puedan introducir pedidos desde el navegador, y que el servidor lea y actualice un fichero de texto plano con el **stock en tiempo real**, manejando correctamente la concurrencia.

---

## ✅ Requisitos del Sistema

### 1. 🌐 Servidor HTTPS con Interfaz HTML

- El servidor debe aceptar **peticiones POST desde un formulario HTML**, donde se seleccione un producto y una cantidad.
- Debe responder **siempre con la misma página HTML**, actualizada con mensajes de éxito o error tras la venta.

**Nota técnica**: Para generar los certificados SSL se debe utilizar `keytool`. Comandos recomendados:

*// Generar Certificado Autofirmado*

```bash
keytool -genkey -alias claveSsl -keyalg RSA -keystore AlmacenSSL
```

*// Exportar Certificado Autofirmado*

```bash
keytool -export -alias claveSsl -keystore AlmacenSSL -rfc -file claveSSL.crt
```

---

### 2. 📦 Productos y Almacenamiento

- **Productos disponibles**:
  - Paracetamol
  - Ibuprofeno
  - Vitamina C

- El stock se guarda en un fichero `stock.txt` con el siguiente formato:

`Paracetamol:10`  
`Ibuprofeno:8`  
`Vitamina C:12`

- Lógica de actualización en cada venta:
  1. Leer el fichero `stock.txt`.
  2. Verificar disponibilidad suficiente del producto.
  3. Restar la cantidad vendida.
  4. Escribir el nuevo stock al fichero.

> ⚠️ Todo el proceso debe estar **sincronizado** para evitar condiciones de carrera entre múltiples peticiones concurrentes.

---

### 3. 📬 Petición y Validación

- El formulario envía una **petición POST** con dos campos:
  - `producto`
  - `cantidad`

- Validaciones requeridas:
  - El producto debe existir.
  - La cantidad solicitada no puede superar el stock disponible.
  - En caso de error, mostrar en la página:  
    `"Stock insuficiente para el producto seleccionado"`

---

### 4. 🔐 Seguridad

- El servidor debe utilizar **HTTPS** y estar correctamente configurado con el `keystore` generado.
- Cualquier ruta que **no sea `/`** debe ser rechazada con un mensaje de error o una página 404 personalizada.

