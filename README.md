<div align="center">

# 📋 PDS Trello — Gestión de Tableros Colaborativos

**Proyecto práctico de la asignatura Procesos de Desarrollo del Software**
Universidad de Murcia · Curso 2025/26

## Participantes

| Nombre | Correo electrónico |
|--------|-------------------|
| Salvador Martínez Sánchez| salvador.m.s@um.es |
| Pedro Chico Caballero | pedro.chicoc@um.es |


---

##  Descripción

Sistema de gestión de trabajo colaborativo mediante tableros de tareas al estilo [Trello](https://trello.com). Permite organizar el trabajo en tableros Kanban con listas y tarjetas, siguiendo una **Arquitectura Hexagonal con Domain-Driven Design (DDD)**.

---

## ✅ Características implementadas

### Funcionalidades básicas
-  Creación y gestión de **tableros** con listas de tareas personalizables
-  **Dos tipos de tarjetas**: tarjetas de tarea y tarjetas con checklist
-  **Etiquetas con color** para clasificar tarjetas
-  Marcado de tarjetas como **completadas** (pasan a lista especial)
-  **Historial de operaciones** de cada tablero (trazabilidad de acciones)
-  **Bloqueo temporal** de tableros (impide añadir nuevas tarjetas durante un periodo)
-  Creación de tablero con **URL única** mediante correo electrónico
-  **Compartir tablero** con otros usuarios mediante URL

### Características opcionales implementadas
- _(Indicar cuáles habéis implementado de la lista del enunciado)_

---

##  Arquitectura

El proyecto sigue una **Arquitectura Hexagonal** con los siguientes módulos:

```
src/
└── main/java/inf/pds/proy/
    ├── domain/             ← Modelo de dominio (DDD)
    │   ├── model/          ← Entidades, Agregados, Value Objects
    │   └── ports/          ← Puertos de entrada y salida
    ├── application/        ← Casos de uso (Use Cases)
    │   └── usecases/
    └── adapters/           ← Adaptadores (Hexagonal)
        ├── jpa/            ← Persistencia con JPA/Hibernate
        ├── mappers/        ← Mappers Dominio ↔ Entidades JPA
        └── ui/             ← Interfaz gráfica JavaFX
```

---

## 🛠️ Tecnologías

| Tecnología | Uso |
|------------|-----|
| **Java 17+** | Lenguaje principal |
| **Spring Boot 3** | Inyección de dependencias, gestión de servicios |
| **JavaFX** | Interfaz gráfica de escritorio |
| **JPA / Hibernate** | Persistencia objeto-relacional |
| **H2 / (BD)** | Base de datos |
| **Maven** | Sistema de construcción |
| **JUnit 5** | Pruebas de software |

---

##  Ejecución

### Requisitos previos
- Java 17 o superior
- Maven 3.8+

### Pasos
```bash
# Clonar el repositorio
git clone https://github.com/SMS-79/PDS-Proyecto.git
cd PDS-Proyecto/PDSProyecto

# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn spring-boot:run
```

---

## Pruebas

```bash
# Ejecutar todos los tests
mvn test

# Ver informe de cobertura (si está configurado JaCoCo)
mvn verify
```

---



<div align="center">

*Procesos de Desarrollo del Software · Universidad de Murcia · 2025/26*

</div>
