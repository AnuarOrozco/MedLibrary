# 🩺 MedLibrary — Medical Wiki Backend

## 📌 Descripción general
**MedLibrary** es una wiki médica orientada a médicos y personal de la salud. Su objetivo es centralizar información confiable y estructurada sobre **medicamentos**, **enfermedades**, **ingredientes activos**, **dosis** e **interacciones**, permitiendo búsquedas eficientes y relaciones claras entre los distintos conceptos clínicos.

El proyecto se desarrolla como un **backend REST moderno**, seguro y escalable, pensado inicialmente como un **MVP sólido**, pero con bases técnicas que permiten evolucionar sin reescrituras importantes.

---

## 🎯 Objetivos del proyecto
- Centralizar información médica de forma estructurada y consistente.
- Facilitar la búsqueda de medicamentos por nombre comercial, genérico, enfermedad o ingrediente activo.
- Modelar correctamente relaciones médicas reales (interacciones, dosis, asociaciones).
- Mantener un diseño limpio, mantenible y fácil de escalar.
- Servir como backend para un frontend web o aplicaciones cliente.

---

## 🚀 Alcance inicial (MVP)
- Consulta de medicamentos y sus detalles clínicos.
- Consulta de enfermedades e ingredientes activos.
- Visualización de interacciones entre medicamentos.
- Seguridad básica basada en usuarios y roles.
- Persistencia en base de datos relacional.
- Entorno dockerizado para desarrollo y despliegue.

---

## 🛠️ Tecnologías

### Backend
- ☕ **Java 21**
- 🌱 **Spring Boot 4**
- 🌐 **Spring Web** (API REST)
- 🗄️ **Spring Data JPA** (persistencia)
- 🔐 **Spring Security + JWT**
- ✅ **Jakarta Validation**
- 📊 **Spring Boot Actuator**

### Base de datos
- 🐘 **PostgreSQL** (desarrollo y producción)
- 🧪 **H2** (testing)

### Infraestructura
- 🐳 **Docker**
- 🧩 **Docker Compose**

### Testing
- 🧪 **JUnit 5**
- 🎭 **Mockito**

### Utilidades
- 🧰 **Lombok**

---

## 🧱 Arquitectura
El proyecto utiliza una **arquitectura en capas (Layered Architecture)** claramente definida:

- **Controller**: expone la API REST.
- **Service**: contiene la lógica de aplicación.
- **Repository**: acceso a datos mediante Spring Data JPA.
- **Domain**: entidades y enums del dominio.
- **Config**: configuración transversal (security, web, persistence).

Este enfoque prioriza **claridad, bajo acoplamiento y mantenibilidad**, evitando complejidad innecesaria en etapas tempranas.

---

## 🧬 Modelo de dominio (resumen)
- **Medication**: información principal de un medicamento.
- **Disease**: enfermedades asociadas.
- **ActiveIngredient**: componentes activos.
- **Dosage**: pautas de dosificación.
- **Interaction**: interacciones entre medicamentos.
- **User**: usuarios del sistema con roles.

Las relaciones están modeladas explícitamente para reflejar la realidad médica y facilitar consultas eficientes.

---

## 🔐 Seguridad
- Autenticación basada en **JWT**.
- Autorización por roles (`ROLE_USER`, `ROLE_ADMIN`).
- Diseño preparado para evolucionar a OAuth2 u otros mecanismos si es necesario.

---

## 🧠 Filosofía del proyecto
- Código limpio y buenas prácticas desde el inicio.
- Dominio primero, infraestructura después.
- Simplicidad consciente: solo se introduce complejidad cuando aporta valor.
- Código pensado para ser leído, entendido y mantenido por otros desarrolladores.
