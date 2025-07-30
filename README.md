# Servicio de Generación de Números Primos

Un microservicio REST desarrollado en Spring Boot que genera números primos de forma asíncrona con una cantidad específica de dígitos.

##  Descripción

Este servicio permite generar números primos aleatorios con una cantidad de dígitos determinada (entre 1 y 18). Los cálculos se ejecutan de forma asíncrona, permitiendo consultar el estado del proceso en tiempo real.

##  Características

-  **Generación asíncrona** de números primos
-  **Validación de entrada** (1-18 dígitos máximo)
-  **Algoritmo eficiente** de verificación de primalidad
-  **API REST** simple y fácil de usar
-  **Containerización** con Docker
-  **Despliegue en Kubernetes** preparado
-  **Procesamiento en segundo plano** con threads

##  Tecnologías Utilizadas

- **Java 21** - Lenguaje de programación
- **Spring Boot 3.5.0** - Framework de desarrollo
- **Maven** - Gestor de dependencias
- **Docker** - Containerización
- **Kubernetes** - Orquestación de contenedores

## 📋 Prerrequisitos

- Java 21 o superior
- Maven 3.6+
- Docker (opcional)
- Kubernetes (opcional)

##  Instalación y Configuración

### Opción 1: Ejecución Local

1. **Clonar el repositorio**
   ```bash
   git clone <url-del-repositorio>
   cd servicio-que-devuelve-un-primo-1
   ```

2. **Compilar el proyecto**
   ```bash
   mvn clean package
   ```

3. **Ejecutar la aplicación**
   ```bash
   java -jar target/demo-0.0.1-SNAPSHOT.jar
   ```

### Opción 2: Con Docker

1. **Construir la imagen**
   ```bash
   ./build.sh
   # o manualmente:
   docker build -t app_demo .
   ```

2. **Ejecutar el contenedor**
   ```bash
   docker run -p 8080:8080 app_demo
   ```

### Opción 3: Con Kubernetes

1. **Construir la imagen Docker**
   ```bash
   docker build -t app_demo .
   ```

2. **Aplicar el manifiesto de Kubernetes**
   ```bash
   kubectl apply -f manifest.yml
   ```

##  Uso de la API

### Endpoints Disponibles

#### 1. Iniciar Proceso de Generación
```http
GET /servicio/{digitos}
```

**Parámetros:**
- `digitos` (int): Cantidad de dígitos del número primo (1-18)

**Respuesta:**
```json
"Proceso iniciado con PID: 1234"
```

#### 2. Consultar Estado del Proceso
```http
GET /servicio/status/{pid}
```

**Parámetros:**
- `pid` (int): ID del proceso a consultar

**Respuestas posibles:**
```json
"En espera..."  // Si el proceso aún está ejecutándose
"El número 12345 generado es primo."  // Si el proceso terminó
"No existe un proceso con PID: 1234"  // Si el PID no existe
```

### Ejemplos de Uso

#### Con curl:
```bash
# Iniciar proceso para generar primo de 5 dígitos
curl http://localhost:8080/servicio/5

# Consultar estado del proceso
curl http://localhost:8080/servicio/status/1234
```

#### Con Postman:
1. Crear una petición GET a `http://localhost:8080/servicio/3`
2. Crear una petición GET a `http://localhost:8080/servicio/status/{pid}`

##  Arquitectura del Proyecto

```
src/main/java/com/example/demo/
├── DemoApplication.java          # Clase principal de Spring Boot
├── controllers/
│   └── PrimoControlador.java     # Controlador REST
├── service/
│   └── Operaciones.java          # Lógica de negocio
└── model/
    └── Proceso.java              # Modelo de datos
```

### Componentes Principales

- **`PrimoControlador`**: Maneja las peticiones HTTP y valida los parámetros
- **`Operaciones`**: Contiene la lógica de generación de primos y gestión de procesos
- **`Proceso`**: Modelo de datos (actualmente no utilizado)

##  Algoritmo de Verificación de Primalidad

El servicio utiliza un algoritmo optimizado que:
1. Verifica casos especiales (números < 2, igual a 2, pares)
2. Comprueba divisibilidad solo hasta la raíz cuadrada del número
3. Itera solo por números impares para mayor eficiencia

##  Flujo de Funcionamiento

1. **Cliente solicita** generación de primo con X dígitos
2. **Sistema valida** el rango (1-18 dígitos)
3. **Se crea proceso asíncrono** con PID único
4. **Proceso ejecuta** durante 5 segundos (simulación)
5. **Cliente consulta** estado usando el PID
6. **Sistema retorna** resultado o estado actual

##  Docker

### Construir Imagen
```bash
docker build -t app_demo .
```

### Ejecutar Contenedor
```bash
docker run -d -p 8080:8080 --name app_demo_container app_demo
```

### Ver Logs
```bash
docker logs app_demo_container
```

##  Kubernetes

El archivo `manifest.yml` incluye:
- **Deployment**: 1 réplica de la aplicación
- **Service**: ClusterIP en puerto 80
- **Configuración**: Para uso local sin registry

### Aplicar Configuración
```bash
kubectl apply -f manifest.yml
```

### Verificar Estado
```bash
kubectl get pods
kubectl get services
```

##  Testing

### Ejecutar Tests
```bash
mvn test
```

### Tests Incluidos
- `DemoApplicationTests`: Tests básicos de la aplicación

##  Notas de Desarrollo

- **Concurrencia**: Utiliza `ConcurrentHashMap` para thread-safety
- **Validación**: Límite de 18 dígitos para evitar overflow de `long`
- **Simulación**: 5 segundos de espera para simular procesamiento pesado
- **PIDs**: Generados aleatoriamente entre 1000-9999

##  Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

##  Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## Autor

Desarrollado como proyecto académico para demostrar conceptos de:
- Microservicios con Spring Boot
- Procesamiento asíncrono
- APIs REST
- Containerización
- Despliegue en Kubernetes

---

**Versión**: 1.0.0  
**Última actualización**: 2024 