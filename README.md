# 🧩 Apex_SC - Sistema de Gestión

Repositorio que contiene la **base de datos**, las **consultas SQL** y los **archivos Java** del sistema Apex_SC.

---

## 📁 Estructura del Repositorio

### 📊 Base de Datos
- **apex_sc.sql** → Script principal con la estructura de la base de datos (tablas creadas sin datos).  
- **tablas_inserciones_consultas.sql** → Script con inserciones de datos y consultas de ejemplo.

### 💻 Código Fuente en Java
- Carpeta **src/apexsc/** → Contiene las clases del sistema desarrolladas en Java:
  - `Socio.java`, `Cuota.java`, `Evento.java`
  - `VentanaSocios.java`, `VentanaCuotas.java`, `VentanaEventos.java`
  - `ApexSC.java` (clase principal del sistema)

---

## 📋 Notas Importantes
- Las tablas en **apex_sc.sql** están creadas pero sin datos.  
- Los datos de prueba fueron eliminados como parte del trabajo práctico.  
- El archivo **tablas_inserciones_consultas.sql** contiene ejemplos de inserciones y consultas.  
- El sistema Java se puede ejecutar directamente desde **NetBeans**, abriendo el proyecto y corriendo la clase principal `ApexSC.java`.

---

## 🔧 Tecnologías Utilizadas
- **Lenguaje:** Java  
- **Entorno:** NetBeans IDE  
- **Base de datos:** PostgreSQL 
- **Interfaz:** Java Swing  

