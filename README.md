# 🧩 Apex_SC – Sistema de Gestión (Java + PostgreSQL)

Este repositorio contiene el **sistema de gestión Apex_SC**, desarrollado en **Java (Swing)** y usando **PostgreSQL** como base de datos.  
Incluye:

- Proyecto completo de NetBeans (en formato ZIP)  
- Base de datos con tablas, secuencias y datos reales  
- Scripts SQL con tablas, inserciones y consultas  
- Documentación del sistema

---

## 📁 Estructura del Repositorio

### 📦 Proyecto Java (NetBeans)
- **ApexSC – Java.zip** → Contiene el proyecto completo:
  - `src/` → Código fuente del sistema  
  - `nbproject/` → Configuración interna de NetBeans  
  - `dist/` → Archivo `.jar` generado y librerías (incluye PostgreSQL driver)  
  - `build.xml`, `manifest.mf`  
- Para ejecutar el sistema, **descomprimir el ZIP y abrir el proyecto en NetBeans**.

---

### 🗄️ Base de Datos

#### **apex_sc.sql**
- Export completo desde PostgreSQL  
- Incluye:
  - Creación de tablas  
  - Creación de secuencias  
  - Relaciones (foreign keys)  
  - **Datos reales de prueba** (socios, cuotas, usuarios, abonos, eventos, entradas)  

#### **tablas_inserciones_consultas.sql**
- Archivo adicional con:
  - Inserciones organizadas por tabla  
  - Consultas de ejemplo para pruebas  

---

## 🛠️ Cómo Restaurar la Base de Datos

1. Abrir **pgAdmin** o cualquier cliente de PostgreSQL.  
2. Crear una base de datos vacía:
   ```sql
   CREATE DATABASE apex_sc;

