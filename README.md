# 📌 Tarea 3 - Aplicación Móvil con Backend

Este repositorio contiene el código de una aplicación móvil nativa en Android que consume un **API REST** desarrollado con **Spring Boot**.

## 📂 Estructura del Proyecto

Tarea3moviles/ │── Ejercicio 1/ │ ├── backend/ # Servidor backend en Spring Boot │ ├── AppRest/ # Aplicación móvil en Android con Retrofit │── README.md # Documentación del proyecto


### 🚀 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

- **Java JDK 17** o superior
- **Gradle** y **Maven**
- **Android Studio** con SDK de Android
- **Spring Boot** y **Spring Tool Suite (STS)** (opcional)
- **Postman** (para probar la API)

---

## 🖥️ 1️⃣ Configuración del Backend (Spring Boot)

1. **Abrir el proyecto:**
   ```bash
   cd "Ejercicio 1/backend"
Compilar y ejecutar el backend:

./mvnw spring-boot:run
(Si usas Windows, ejecuta mvnw.cmd spring-boot:run)

Verificar que la API está funcionando:
Abre en el navegador o Postman:
http://localhost:8080/api/mensaje
Debe responder con un JSON como:

json
Copy
Edit
{
  "mensaje": "¡Hola desde el backend!"
}
 Configuración de la Aplicación Android
Abrir el proyecto en Android Studio:

Ir a Ejercicio 1/AppRest/
Abrirlo con Android Studio
Asegurar que Retrofit está configurado en build.gradle.kts:

kotlin
Copy
Edit
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
Revisar la configuración de RetrofitClient.kt:

kotlin
Copy
Edit
object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
Ejecutar la app en un emulador o dispositivo real.
Si todo está bien, la app mostrará el mensaje recibido del backend.

🎯 Notas Finales
10.0.2.2 se usa en emuladores para acceder a localhost:8080 del backend.
Para dispositivos físicos, reemplaza BASE_URL con la IP de tu PC en la red local.
Si hay errores de conexión, revisa firewall y permisos de red en el backend.
