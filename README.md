
                 PROYECTO: Cifrado de datos de clientes
                          VERSIÓN: 1.0
                      FECHA: 18 de junio de 2025
-----------------------------------------------------------------

AUTORES:
- Gerardo Mauricio Aguilar Chávez – 00195721
- Rodolfo Rafael García Castillo – 00082421
- Carlos Alberto Hernández Guerra – 00002721
- Ricardo Alexander Lopez Hernandez – 00107521
- Billy Rene Valencia Marroquín  – 00124621

CURSO: Administración de Riesgos Informáticos
CICLO: I-2025
DOCENTE: Jaime Roberto Clímaco

-----------------------------------------------------------------
DESCRIPCIÓN:
-----------------------------------------------------------------
Este proyecto consiste en el desarrollo de una aplicación web enfocada
en la conversión y cifrado de archivos que contienen datos sensibles de 
clientes, especialmente números de tarjeta de crédito o débito. El sistema 
permite subir archivos de texto plano con múltiples registros, procesarlos 
para transformarlos a formatos JSON o XML, y aplicar un cifrado seguro sobre
los campos confidenciales como el numero de tarjeta.

Funcionalidades:
- Genera y permite guardar un documento XML o JSON a partir de un archivo de texto
(txt), donde los datos en el archivo de texto están separados por delimitadores.

- Genera y permite guardar un archivo de texto (txt) separado por delimitadores a 
partir de un XML o de un JSON.

- Cifra el dato del número de tarjeta de crédito en la generación del XML o JSON, y
descifra cuando se genere el archivo texto, solicitando una llave al usuario cuando
corresponde asi como el delimitador. 

El objetivo principal es demostrar una solución completa de procesamiento seguro 
de datos desde una interfaz web moderna, utilizando tecnologías como React para el 
frontend y Spring Boot en el backend. La aplicación incluye validación, cifrado, 
visualización del resultado y descarga del archivo transformado, todo manteniendo 
una arquitectura modular y escalable.

-----------------------------------------------------------------
ESTRUCTURA DEL PAQUETE:
-----------------------------------------------------------------
/fuentes/               → Código fuente completo del proyecto.
/fuentes/frontend/      → Código fuente del frontend.
/fuentes/backend/       → Código fuente del backend.

manual_usuario.pdf      → Documento con instrucciones de instalación,
                          uso y flujo general del sistema.
portada_equipo.pdf      → Portada con identificación del grupo,
                          asignatura, ciclo y docente evaluador.

-----------------------------------------------------------------
REQUISITOS DEL SISTEMA:
-----------------------------------------------------------------
SISTEMA OPERATIVO:
- Windows 10 o superior

REQUISITOS DE RED:
- Acceso a puertos locales:
  - Puerto 5173 (Frontend)
  - Puerto 8080 (Backend)

DEPENDENCIAS DEL PROYECTO:

  FRONTEND (React + Vite + Tailwind):
  - Node.js v18.x o superior
  - npm v9.x o superior
  - React 19.1.0
  - Vite 6.3.5
  - TailwindCSS 3.4.1
  - TypeScript 5.8.3
  - ESLint y plugins para análisis de código

  BACKEND (Spring Boot):
  - Java JDK 17
  - Gradle con los siguientes plugins:
    - org.springframework.boot 3.5.0
    - io.spring.dependency-management 1.1.7
  - Spring Web, Mustache, Validation
  - Jackson (formato XML y JSON)
  - Apache Commons CSV 1.10.0
  - Lombok 1.18.32 (compilación y anotaciones)
  - Spring Boot DevTools (recarga en caliente)
  - JUnit 5 (JUnit Platform Launcher)

BASE DE DATOS:
- No requiere base de datos persistente por defecto.

HERRAMIENTAS DE DESARROLLO RECOMENDADAS:
- Editor de código: Visual Studio Code o IntelliJ IDEA
- Navegador: Google Chrome o Mozilla Firefox
- Herramientas adicionales: Postman (para pruebas de API REST)

-----------------------------------------------------------------
INSTRUCCIONES DE INSTALACIÓN:
-----------------------------------------------------------------

1. Clonar el repositorio desde GitHub

2. Acceder a las carpetas de fuentes:
   - Frontend: /fuentes/frontend/
   - Backend: /fuentes/backend/

3. INSTALACIÓN DEL FRONTEND:
   a) Abrir terminal en /fuentes/frontend/
   b) Ejecutar:
      npm install
   c) Esperar que se instalen las dependencias.

4. INSTALACIÓN DEL BACKEND:
   a) Abrir terminal en /fuentes/backend/
   b) Ejecutar:
      ./gradlew build
   c) Esperar que se compile correctamente.

*NOTA:* Asegúrate de tener instalados Node.js, npm y como minimo Java 
JDK 17 antes de iniciar la instalación.


-----------------------------------------------------------------
EJECUCIÓN DEL SISTEMA:
-----------------------------------------------------------------

1. Iniciar el backend:
   - Abrir una terminal en /fuentes/backend/
   - Ejecutar: ./gradlew bootRun
   - Verificar que la API REST está corriendo en http://localhost:8080

2. Iniciar el frontend:
   - Abrir otra terminal en /fuentes/frontend/
   - Ejecutar: npm run dev
   - Acceder a la interfaz en el navegador desde: http://localhost:5173

3. Validación del sistema:
   - Verificar integración completa entre frontend y backend
   - Probar la conversión de archivos y cifrado de datos sensibles
   - Asegurarse que las respuestas del backend se reflejan correctamente 
   - en la interfaz

*NOTA:* Si se presenta algún error, revisar los mensajes en consola de 
ambas terminales y verificar la configuración del entorno.

-----------------------------------------------------------------
NOTAS ADICIONALES:
-----------------------------------------------------------------
- El sistema fue probado en navegadores actualizados (Chrome, Brave).
- Se recomienda mantener actualizadas las dependencias.
- Para entornos de producción, es necesario configurar certificados SSL
  y mecanismos de autenticación robusta.


