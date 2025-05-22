# Ejercicio2: Fistro Servidooorrrrr  
*(Diseñada por Felipe G.R.)*  

🕸 **Práctica**: Servidor HTTPS con respuestas aleatorias de Chiquito de la Calzada

---

## 📄 Descripción

El objetivo de esta práctica es desarrollar una aplicación cliente-servidor **HTTPS en Java** que sirva una página HTML interactiva.  
El servidor debe generar dinámicamente una página HTML con un botón titulado **"AL ATAQUERRR!!"**.  
Al pulsar dicho botón, se enviará una petición **POST** al servidor. Este devolverá la misma página HTML con una **frase aleatoria** de Chiquito de la Calzada justo debajo del botón.

---

## 🎯 Requisitos

- El servidor debe funcionar bajo el protocolo **HTTPS** utilizando un **certificado SSL autofirmado** (para pruebas locales).
- El contenido HTML debe **generarse manualmente como texto** dentro del código Java.
- El botón debe estar contenido en un **formulario con método POST**.
- Al hacer clic en el botón, se debe mostrar en la misma página una **frase aleatoria** obtenida de una colección de frases predefinidas.
- El servidor debe ser capaz de **manejar múltiples peticiones concurrentes utilizando hilos (threads)**.
- El servidor debe manejar excepciones y errores de forma adecuada, devolviendo un mensaje de error en caso de fallo.
- El servidor debe cerrar conexiones de forma segura y **liberar recursos al finalizar**.
- Se debe gestionar la concurrencia de peticiones utilizando **métodos sincronizados o bloqueos (locks)** para evitar condiciones de carrera.

---

## 🔑 Certificado SSL

Para generar un certificado SSL autofirmado, utiliza el siguiente comando de `keytool`:

```bash
keytool -genkeypair -alias mydomain -keyalg RSA -keystore keystore.jks -storepass password -validity 365
```

Donde:

- `mydomain` es el alias del certificado.
- `keystore.jks` es el nombre del archivo donde se guardará el certificado.
- `password` es la contraseña del keystore.
- `365` es el número de días de validez del certificado.

> Asegúrate de que:
> - El archivo `keystore.jks` se encuentre en la misma carpeta que tu código Java o proporciona la ruta completa.
> - El puerto elegido para el servidor HTTPS (por ejemplo, `8443`) esté disponible y libre.

---

## 🚀 Posibles mejoras optativas

- Implementar un sistema de **logging** para registrar peticiones y respuestas, por ejemplo:
  - `[2017-05-18 14:04:07] [INFO] petición recibida y procesada por el servidor`
  - `[2017-05-18 14:04:07] [ERROR] error al procesar la petición recibida`
- Obtener las frases desde un archivo `frases.txt` en lugar de codificarlas en el programa.
- Añadir un **contador de peticiones** para mostrar cuántas veces se ha pulsado el botón.

---

## 💡 Comportamiento esperado

1. El cliente accede a `https://localhost:puerto/`.
2. Se muestra una página HTML con un título, un botón **"AL ATAQUERRR!!"**, y sin mensajes al inicio.
3. Al pulsar el botón, se realiza una **petición POST** al servidor.
4. El servidor responde con la misma página HTML, incluyendo **una frase aleatoria** bajo el botón.

---

## 📚 Sugerencias

- Se recomienda generar el certificado SSL con la herramienta `keytool`.
- La colección de frases puede implementarse como una **lista estática en Java**.
- La lógica para seleccionar frases debe garantizar **aleatoriedad real**.

