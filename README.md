
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
