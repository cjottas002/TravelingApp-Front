# 🌍 TravelingApp

**TravelingApp** es una solución completa de gestión de viajes que incluye:

- ✨ Frontend Android (Kotlin + XML + MVVM)
- 💼 Backend en .NET 8 (C#) con arquitectura limpia y CQRS
- 🗄️ Persistencia en base de datos SQL Server con Entity Framework Core
- 🐳 Contenerización con Docker y Docker Compose

---

## 📱 Frontend (Android)

El frontend está desarrollado en **Kotlin** siguiendo buenas prácticas de arquitectura moderna:

- ✅ **MVVM** (Model-View-ViewModel)
- ✅ **Diseño con XML** (layouts tradicionales)
- ✅ **Jetpack Libraries** (LiveData, Navigation, ViewBinding, Lifecycle)
- ✅ **Retrofit** para llamadas HTTP
- ✅ **Dagger Hilt** para inyección de dependencias
- ✅ **Modularización** para mantener una arquitectura escalable

### 📁 Módulos principales

- `core`: configuración global (Retrofit, interceptores, etc.)
- `data`: modelos de red y DTOs
- `domain`: entidades para la ui, implementacion de repositorios, servicios
- `ui`: vistas XML, viewmodels y lógica de presentación

---

## 🧠 Backend (.NET)

El backend está desarrollado con **.NET 8** en C#, aplicando principios de arquitectura limpia y CQRS.

### 🛠️ Tecnologías y patrones usados

- **CQRS** con `MediatR` para separar lectura y escritura
- **AutoMapper** para mapear entre entidades y DTOs
- **Entity Framework Core** para acceso a datos
- **FluentValidation** para validaciones limpias de comandos
- **JWT** para autenticación segura
- **ASP.NET Core Identity** para gestión de usuarios y roles

